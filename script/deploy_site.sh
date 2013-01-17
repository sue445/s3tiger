#!/bin/bash

readonly WORKSPACE=$1
readonly BUILD_NUMBER=$2
readonly BUILD_ID=$3

readonly TMP_DIR=tmp
readonly REPO_URL="git@github.com:sue445/s3tiger.git"
readonly BRANCH="gh-pages"

rm -rf ${WORKSPACE}/${TMP_DIR}

git clone ${REPO_URL} --branch ${BRANCH} ${TMP_DIR}

cd ${TMP_DIR}

rm -rf ./site/*
cp -r ../site/* ./site/

git add .
git commit -am "build by jenkins : ${BUILD_NUMBER} ${BUILD_ID}"

git push origin ${BRANCH}

