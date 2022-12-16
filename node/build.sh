#!/bin/bash

set -eux

rm -rf node.deployer-4.2.18.tar.gz

tar czvf node.deployer-4.2.18.tar.gz node.deployer-4.2.18

BUILD_NAME='tl-otter'
BUILD_VERSION='pat-otter-node-4.2.18'
BUILD_HOST='toplist-registry.cn-shanghai.cr.aliyuncs.com/88'

docker build -t $BUILD_NAME:$BUILD_VERSION -f Dockerfile .

docker images | grep $BUILD_VERSION

IMAGETAG=$(docker images | grep $BUILD_VERSION | head -n 1 | awk '{print $3}')

echo $IMAGETAG

docker tag $IMAGETAG $BUILD_HOST/$BUILD_NAME:$BUILD_VERSION

docker push $BUILD_HOST/$BUILD_NAME:$BUILD_VERSION

