#!/bin/bash
# clean out any previously generated terasort data
hadoop fs -rm -r input-data/terasort-input
hadoop fs -rm -r output-terasort
# create a 10MB dataset. Command syntax is:
# hadoop jar hadoop-*examples*.jar teragen <number of 100 byte rows> <output-dir>
hadoop jar /usr/share/hue/apps/oozie/examples/lib/hadoop-examples.jar teragen 100000 input-data/terasort-input
# this terasort-input dir now has 2 5MB files with gobbly-gook in them

# RUN THE ACTUAL BENCHMARK
# syntax is:
# hadoop jar hadoop-*examples*.jar terasort <input dir> <output dir>
hadoop jar /usr/share/hue/apps/oozie/examples/lib/hadoop-examples.jar terasort input-data/terasort-input output-terasort
# then open web gui & browse the seconds to complete for this job -- on our VM
# it runs about 30 seconds. .5min * 10,000 (to get a terasort number) = 5,000 mins
# pretty horrid! (but then again, this is in a VM)