#!/bin/sh

commit_regex='^((feat|fix|build|ci|docs|refactor|style|test|perf|chore)(\(\w[-\w]*\))?(: (.*\s*)*))|(Merge (.*\s*)*)|(Revert (.*\s*)*)'
error_msg="Aborting commit. Your commit message doesn't fit the expected pattern."


if ! grep -iqE "$commit_regex" "$1";
then
    echo "$error_msg" >&2
    exit 1
fi