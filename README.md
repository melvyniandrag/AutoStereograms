# How to make autostereograms

## Magic Eye
I wanted to know how to make a magic eye like those books we had as kids in the 90s. Found a few good explanations on line and whipped up an implementation reallly quick.

As of tag v1.0 the code is brute force, not beautiful at all. If you see some commits after that date then I probably spent time to clean it up.

I just wanted  to make it work! And it does! So goodnight. Curiosity assuaged.

## TODO:
* Add more pixel shift values. Right now I just shift dark pixels by X amount and light pixels by Y amount.
  should have more shifts for different shades of gray
* right now I leave a black background after I shift the pixels. Read in some reference below that we can fill the black space 
in with new random noise after we shift. Need to test.

## References:
Of course these links can die at any moment.... read while the reading's good ya'll!

* https://developer.nvidia.com/gpugems/gpugems/part-vi-beyond-triangles/chapter-41-real-time-stereograms
* https://www.ime.usp.br/~otuyama/stereogram/basic/index.html
* https://en.wikipedia.org/wiki/Autostereogram


