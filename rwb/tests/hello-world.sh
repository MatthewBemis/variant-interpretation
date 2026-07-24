#!/bin/bash
set -o errexit
export TEST_USER="root"

CONTAINER_NAME="application-server"

echo "Checking that the search entry page is served by default..."
result="$(docker exec "$CONTAINER_NAME" wget -qO- http://localhost:8080)"
if [[ "$result" != *"Variant Interpretation"* ]]; then
  echo "ERROR: Expected default page to contain 'Variant Interpretation', got: ${result}"
  exit 1
fi

echo "Checking that the results page is served..."
result="$(docker exec "$CONTAINER_NAME" wget -qO- http://localhost:8080/via-search-results-v1.4.html)"
if [[ "$result" != *"Candidate variants"* ]]; then
  echo "ERROR: Expected results page to contain 'Candidate variants', got: ${result}"
  exit 1
fi

bats tests/common/base.bats
