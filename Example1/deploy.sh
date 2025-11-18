#!/bin/bash
DEST="D:\CoreFi\workspace\gitops-demo\tmp\deployed-web"
mkdir -p $DEST
cp config/index.html $DEST/
echo "Deployed to $DEST:"
cat $DEST/index.html
mkdir hello1
