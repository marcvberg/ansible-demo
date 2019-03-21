from ansible.module_utils.basic import *

import boto3
import botocore

DOCUMENTATION = """
---
module: storage
short_description: Retrieves files from s3
description:
  - Takes in an s3 bucket and file and writes it out to a destination using defined AWS credentials
author: Marc VandenBerg
notes:
  - Resolve any dependencies in the requirements before use
requirements:
  - boto3
  - ~/.aws/credentials
options:
  bucket:
    description:
      - The name of the s3 bucket being read from
    required: true
    default: null
  file:
    description:
      - The name of the file in the s3 bucket
    required: true
    default: null
  dest:
    description:
      - Absolute path on remote server where the binary should written
    required: true
    default: null
"""

EXAMPLES = """
- action: storage bucket=test-bucket file=some-file-name.txt dest=/tmp/test.txt
"""


def main():
    module = AnsibleModule(
        argument_spec = dict(
            bucket = dict(required = True),
            file = dict(required = True),
            dest = dict(required = True)
        )
    )
    
    try:
        s3 = boto3.resource('s3')
        s3.Bucket(module.params['bucket']).download_file(module.params['file'], module.params['dest'])
        return True
    except botocore.exceptions.ClientError:
        return False


if __name__ == "__main__":
    main()
