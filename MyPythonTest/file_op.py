#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import re
import sys
import pandas as pd

print(sys.argv)
file_path = sys.argv[1]
print(file_path)
fo = open(file_path, "r+")
data_list = []
for line in fo.readlines():
    if "INFO" in line and "bomId" in line:
        line = line.strip()
        line = line.split(" ")
        data_list.append(
            {
                "pull_time": line[2],
                "bomid": re.findall("\d+", line[15])[0]
            }
        )
fo.close()

df = pd.DataFrame(data_list)

