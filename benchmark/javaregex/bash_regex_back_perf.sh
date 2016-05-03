#!/bin/bash

B="a"
A="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaac";

for i in `seq 1 1000`; do
  ts=$(date +%s%N)
  A=$B$A 
  echo $A | egrep "(a|aa)*c"  
  tt=$((($(date +%s%N) - $ts)/1000000))
  echo $A' in '$tt'ms'
done
