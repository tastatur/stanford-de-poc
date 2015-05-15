#!/bin/bash

mvn install:install-file -Dfile=lib/stanford-ner-resources.jar -DgroupId=edu.stanford.nlp -DartifactId=stanford-resources -Dversion=1.0 -Dpackaging=jar
