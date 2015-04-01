#!/usr/bin/python

import sys

f = open(sys.argv[1])
text = f.read()
text = text.replace('x', '#')
f.close()

f = open(sys.argv[1], "wb+")
f.write(text)

