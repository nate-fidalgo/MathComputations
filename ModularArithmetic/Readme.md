
### Modular Residue calculator 

<br>
This is used to compute a given residue class
<br>
For example most people understand how to do basic modular arithmetic like <code> 4 mod 3 = 1 , 6 mod 2 = 0</code>. However start introducing powers like <code>x^2 mod n</code> it get more complex where gauss quadratic reciprocity laws come in. Less people have probably heard of that and then you can go further to cubic reciprocities <code>x^3 mod n</code> ,.... on and on the most general of reciprocity laws probably being artins reciprocities. This small program allows one to compute the residue class of any x^m mod n aka the mth reciprocity mod n. ( the mth residue class mod n )

<br>
To run the program type 
<code>java Residue</code>
<br>
From there that should yield you something like below where you then enter your modulus n and your power m hitting enter and the program gives back the residue class (this example was for x^5 mod 456 notice not always ever number from 0 thru (456 - 1)  shows up like with standard x^1 mod n or another words regular x mod n arithmetic these are called the nonresidue numbers. For all intents and purpose i only display the residue class not the nonresidue class mod n.
But the opposite is easy to figure out if you got the residue class so perhaps in future i will add a function to display the nonresidue class mod n
<pre>
<code>
---Modular Residue Calculator---
Enter the modulus 
456
Enter the power 
5
Residue class : [ 0 1 32 243 112 389 24 391 392 225 136 83 312 109 200 135 232 329 360 19 248 165 376 359 408 385 296 411 16 269 216 103 128 105 40 251 120 37 152 39 184 281 264 427 176 429 88 263 288 121 8 147 352 173 384 367 56 57 400 203 72 397 104 423 448 449 168 307 368 141 280 143 192 289 272 75 304 77 336 319 416 9 328 11 240 301 440 159 160 185 48 211 80 405 208 95 96 241 224 435 256 5 144 31 320 345 64 179 432 181 344 327 424 113 0 115 32 357 112 47 24 49 392 339 136 197 312 223 200 249 232 443 360 133 248 279 376 17 408 43 296 69 16 383 216 217 128 219 40 365 120 151 152 153 184 395 264 85 176 87 88 377 288 235 8 261 352 287 384 25 56 171 400 317 72 55 104 81 448 107 168 421 368 255 280 257 192 403 272 189 304 191 336 433 416 123 328 125 240 415 440 273 160 299 48 325 80 63 208 209 96 355 224 93 256 119 144 145 320 3 64 293 432 295 344 441 424 227 0 229 32 15 112 161 24 163 392 453 136 311 312 337 200 363 232 101 360 247 248 393 376 131 408 157 296 183 16 41 216 331 128 333 40 23 120 265 152 267 184 53 264 199 176 201 88 35 288 349 8 375 352 401 384 139 56 285 400 431 72 169 104 195 448 221 168 79 368 369 280 371 192 61 272 303 304 305 336 91 416 237 328 239 240 73 440 387 160 413 48 439 80 177 208 323 96 13 224 207 256 233 144 259 320 117 64 407 432 409 344 99 424 341 0 343 32 129 112 275 24 277 392 111 136 425 312 451 200 21 232 215 360 361 248 51 376 245 408 271 296 297 16 155 216 445 128 447 40 137 120 379 152 381 184 167 264 313 176 315 88 149 288 7 8 33 352 59 384 253 56 399 400 89 72 283 104 309 448 335 168 193 368 27 280 29 192 175 272 417 304 419 336 205 416 351 328 353 240 187 440 45 160 71 48 97 80 291 208 437 96 127 224 321 256 347 144 373 320 231 64 65 432 67 344 213 424 455 ] 

 </code>
</pre>  

<br>
Notice in this example 2 is not in the residue class so its a nonresidue class number however 3 is in the residue class if you look hard enough "or do a find on it".  This all means the x^5 mod 456 can equal 3 but it cannt equal 2 so that means an equation of the form <br> <code> x^5 = 456*k + 2  has no integer solutions </code> but <code> x^5 = 456*k + 3 has solutions </code>

<br>
If you think deeper this gives away to quickly find types of diophaintine equations or tons of equations with no solutions in integers just by looking for nonresidue based numbers.
<br>
<br>
Stay tuned more to come probably going to add functions to compute the primitive roots , primative kth roots of unity mod n  ,...etc
<br>
As well as fermat little theorem / euler theorem , applications to test for probable primes , and perhaps discussion on the discrete log problem
