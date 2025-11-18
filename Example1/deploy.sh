#!/bin/bash
DEST="/home/configuser/Example1/tmp/deployed-web"
mkdir -p $DEST
cp config/index.html $DEST/
echo "Deployed to $DEST:"
cat $DEST/index.html

