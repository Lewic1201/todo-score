#!/usr/bin/env bash
nohup java -Xms128m -Xmx512m -XX:+HeapDumpOnOutOfMemoryError -jar todo-score-0.0.1-SNAPSHOT.jar &
