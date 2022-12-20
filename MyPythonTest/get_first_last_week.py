#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import datetime


def get_first_last_week(curr_date):
    monday = curr_date - datetime.timedelta(curr_date.weekday())
    sunday = monday + datetime.timedelta(6)

    return datetime.datetime.strftime(monday, '%Y.%m.%d') + '-' + datetime.datetime.strftime(sunday, '%Y.%m.%d')


if __name__ == '__main__':
    today = datetime.datetime(year=2022, month=10, day=11)
    str = get_first_last_week(today)
    print(str)

    today = datetime.datetime(year=2022, month=10, day=10)
    str = get_first_last_week(today)
    print(str)

    today = datetime.datetime(year=2022, month=10, day=16)
    str = get_first_last_week(today)
    print(str)

    today = datetime.datetime(year=2022, month=10, day=1)
    str = get_first_last_week(today)
    print(str)
