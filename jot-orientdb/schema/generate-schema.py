#!/usr/bin/env python3

"""
Support for provisioning Jot schemas in an OrientDB instance.

Usage: generate-schema [-m <schema_manifest>]
"""
import argparse
import pyorient
import yaml

try:
    from yaml import CLoader as Loader
except ImportError:
    from yaml import Loader


def parse_manifest(file):
    return yaml.load(file, Loader=Loader)


def generate_schema(client, funcs):
    for name, data in funcs.items():
        with open(data['code'], 'r') as f:
            code = f.read()

        query_func = "SELECT FROM OFunction WHERE name = '%s'" % name
        if len(client.command(query_func)) == 0:
            osql = "CREATE FUNCTION %s '%s'" % (name, code)

            if 'args' in data:
                osql += ", parameters = '%s'" % str(data['args'])

            osql += " WHERE name = '%s'" % name

            client.command(osql)


def main():
    parser = argparse.ArgumentParser(description='Jot Schema Tool.')
    parser.add_argument('-m', '--manifest', metavar='<manifest_path>', default='schema.yml', type=argparse.FileType('r'),
                        help='location of manifest file (default: %(default)s)')

    args = parser.parse_args()

    client = pyorient.OrientDB("localhost", 2480)
    session_id = client.connect('admin', 'admin')
    manifest = parse_manifest(args.manifest)
    generate_schema(client, manifest)

    client.shutdown('root', 'password')


if __name__ == "__main__":
    main()
