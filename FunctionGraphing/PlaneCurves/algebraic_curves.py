
'''

 _______  _        _______  _        _______    _______           _______           _______  _______ 
(  ____ )( \      (  ___  )( (    /|(  ____ \  (  ____ \|\     /|(  ____ )|\     /|(  ____ \(  ____ \
| (    )|| (      | (   ) ||  \  ( || (    \/  | (    \/| )   ( || (    )|| )   ( || (    \/| (    \/
| (____)|| |      | (___) ||   \ | || (__      | |      | |   | || (____)|| |   | || (__    | (_____ 
|  _____)| |      |  ___  || (\ \) ||  __)     | |      | |   | ||     __)( (   ) )|  __)   (_____  )
| (      | |      | (   ) || | \   || (        | |      | |   | || (\ (    \ \_/ / | (            ) |
| )      | (____/\| )   ( || )  \  || (____/\  | (____/\| (___) || ) \ \__  \   /  | (____/\/\____) |
|/       (_______/|/     \||/    )_)(_______/  (_______/(_______)|/   \__/   \_/   (_______/\_______)
                                                                                                     

This program generates a python script that displays any plane algebraic curves that you want to graph.
As input a file containing the algebraic curves fields delimited by |

Example Input File

y^2 = x^3 + x^2 | (-2,6,100) | (-4,8,200)
x^3+1 | (3,60,900)
x^7 | (-2,7,400)

where  format is 
plane algebraic curve | (xstart,xend,precision) | (ystart,yend,precision)

If the program errors out or hangs chances are your input file is not in the proper above format

'''

from sys import argv 

CRED = '\033[91m'
CEND = '\033[0m'
BOLD = '\033[1m'
NEWLINE = '\n'

FUNC_DECLARE_INDEX=1
X_INDEX = 2
F_INDEX=3
C_INDEX=3
Y_INDEX = 4
M_INDEX = 5


WRITEFUNCTIONS = []
WRITEXSTATEMENTS = []
WRITEYSTATEMENTS = []
WRITEMSTATEMENTS = []
WRITEPSTATEMENTS = []
WRITECSTATEMENTS = []



IMPORTCODE = """
import numpy as np
import matplotlib.pyplot as plt 

"""

CODE = [] 

def buildProcessCode( ifname , ofname="PYPLOTS.py" ) :

	mfile= open(ifname , "r")
	mplotexps = mfile.readlines()

	linecount = len(mplotexps)
	i=0
	while i < linecount :
		if mplotexps[i].strip() == "" :
			i+=1	
			continue
		else :		
			mplotexp = mplotexps[i].split("|")
			print(mplotexp)
			function = mplotexp[0].strip()
			if isImplicitFunction(function) == True :
				xcoordinates = mplotexp[1]
				ycoordinates = mplotexp[2]
				sment = parseIntoContourPlotCode(function,xcoordinates,ycoordinates,i)
				CODE.append(sment)
			else:
				xcoordinates = mplotexp[1]
				sment = parseIntoPlotCode(function,xcoordinates,i)
				CODE.append(sment)
		i+=1
		print(i)

	mfile.close()
	buildProcessingFile(ofname)
	return





def isImplicitFunction(func) :
	stest = func.split("=") 
	if len(stest) == 2 :
		return True
	return False


def parseIntoContourPlotCode(impfunc,xcoords,ycoords,i) :
	func  = impfunc.split("=")
	sfunc = func[1] + " - (" + func[0] + ")"
	
	xcoords = xcoords.split( "(" )[1]
	xcoords = xcoords.split( ")" )[0]
	xparams = xcoords.split( "," )
	xstart = xparams[0]
	xends  = xparams[1]
	xprecision = xparams[2]

	ycoords = ycoords.split( "(" )[1]
	ycoords = ycoords.split( ")" )[0]
	yparams = ycoords.split( "," )
	ystart = yparams[0]
	yends  = xparams[1]
	yprecision = yparams[2]

	xstr = "x"+str(i)
	ystr = "y"+str(i)
	fstr = "f"+str(i)
	xcoordstr = "ax"+str(i)
	ycoordstr = "ay"+str(i)
	funcdeclarestatement = fstr + " = lambda x,y: " + sfunc
	xstatement = xcoordstr + " = np.linspace(" + xstart +"," + xends + "," + xprecision + ")"
	ystatement = ycoordstr + " = np.linspace(" + ystart +"," + yends + "," + yprecision + ")"

	meshstatement = xstr +","+ ystr + "= np.meshgrid("+xcoordstr+","+ ycoordstr+")"
	level = '0'
	funcplotstatement = "plt.contour("+ xstr +"," + ystr +"," + fstr + "("+xstr+","+ystr+")" + "," + level+")"

	return ["Contour",funcdeclarestatement,xstatement,funcplotstatement,ystatement,meshstatement]


def parseIntoPlotCode(func,xcoords,i) :
	print(xcoords)
	xcoords = xcoords.split( "(" )[1]
	print(xcoords)
	xcoords = xcoords.split( ")" )[0]
	print(xcoords)
	xparams = xcoords.split( "," )
	print(xparams)
	xstart = xparams[0]
	xends  = xparams[1]
	xprecision = xparams[2]

	xcoordstr = "x"+str(i)
	fstr = "f"+str(i)
	funcdeclarestatement = fstr + " = lambda x: " + func
	funcplotstatement = "plt.plot(" + xcoordstr + "," + fstr + "("+xcoordstr+")" + ")"  
	xstatement = xcoordstr + " = np.linspace(" + xstart +"," + xends + "," + xprecision + ")"

	return ["Plot",funcdeclarestatement,xstatement,funcplotstatement]




def buildProcessingFile(oname) :
	i=0
	while i < len(CODE) :
		WRITEFUNCTIONS.append( CODE[i][FUNC_DECLARE_INDEX] )
		if CODE[i][0] == 'Plot' :
			WRITEXSTATEMENTS.append(CODE[i][X_INDEX])
			WRITEPSTATEMENTS.append(CODE[i][F_INDEX])
		else:
			WRITEXSTATEMENTS.append(CODE[i][X_INDEX])
			WRITEYSTATEMENTS.append(CODE[i][Y_INDEX])
			WRITEMSTATEMENTS.append(CODE[i][M_INDEX])
			WRITECSTATEMENTS.append(CODE[i][C_INDEX])

		i+=1


	writeProcessFile(oname)
	return 
	

def writeProcessFile(oname) :

	mfile= open(oname , "w")
	mfile.write( IMPORTCODE ) 

	i=0
	while i < len(WRITEFUNCTIONS) :
		WRITEFUNCTIONS[i] = WRITEFUNCTIONS[i].replace("^","**")
		mfile.write( WRITEFUNCTIONS[i] + NEWLINE )
		i+=1

	mfile.write( NEWLINE )
	i=0
	while i < len(WRITEXSTATEMENTS) :
		mfile.write( WRITEXSTATEMENTS[i] + NEWLINE )
		i+=1

	mfile.write( NEWLINE )
	i=0
	while i < len(WRITEYSTATEMENTS) :
		mfile.write( WRITEYSTATEMENTS[i] + NEWLINE )
		i+=1

	mfile.write( NEWLINE )	
	i=0
	while i < len(WRITEMSTATEMENTS) :
		mfile.write( WRITEMSTATEMENTS[i] + NEWLINE )
		i+=1

	mfile.write( NEWLINE )
	i=0
	while i < len(WRITECSTATEMENTS) :
		mfile.write( WRITECSTATEMENTS[i] + NEWLINE )
		mfile.write( "plt.show()" + NEWLINE )
		i+=1
	
	mfile.write( NEWLINE )
	i=0
	while i < len(WRITEPSTATEMENTS) :
		mfile.write( WRITEPSTATEMENTS[i] + NEWLINE )
		mfile.write( "plt.show()" + NEWLINE )		
		i+=1
	

	mfile.close()
	return


def checkifexists(filename) :
	try :	
		f=open(filename,"r")
		f.close()
		return
	except:
		print("Input file doesnt exist")
		exit()

istr = input("Enter your input file to be processed")

warnmsg = """Enter an output file or hit enter for default 
warning default is named PYPLOTS.py it will be overwritten
if it exists unless you supply your own"
"""
ostr = input( BOLD + CRED + warnmsg + CEND )	

checkifexists(istr)

if ostr.strip() == "" :
	buildProcessCode( istr )
else:
	buildProcessCode( istr , ostr )


print(argv)

