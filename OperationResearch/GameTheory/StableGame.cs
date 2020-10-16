using System;

/****
 *  This program is written in C# programming language!
 *  The program  is for solving all two-player zero-sum stable games. Later i plan on implementing code to handle all unstable two-player zero-sum games.
 *  But for now it just covers all stable games its definitely a project for somebody that is learning c# programming and discrete math subject areas.
 *  So geared for  computer science students as well as mathematics students that are programming inclined!
 *  Most professors around the area seem to leave out this important game theory topic area in teaching discrete mathematics course.
 *  Its more so used in economics math but make no mistake it should be in the mathematicians tool belt and computer programmers get a different flavor of programming that's not usually done in
 *  textbooks.
 *
 *  Also note obviously computers can only hand a finite matrix so you can only analysis games with a finite number of rows /columns for the payoff matrix.
 *  The two players can only have finite strategies though in reality games can have infinite size row /column payoff matrices we dont consider the case when players can have infinite strategies
 *  Even  though in reality most games the player has infinite strategies , However most games came be model by throwing out all the irrelevant strategies and just focus on a select finite strategies
 *  So Finite is the game. If you want to get into infinite size payoff matrices or infinite size graph theory with infinite row/col size adjacent matrix that something handle more by use mathematicians
 *  Then by a computer program.
 *  Just to make people aware they call infinite strategy games => infinity game theory , and infinite graph theory => extremal graph theory (Paul Edros was know for his pioneering  of extremal graph theory
 *  But this is getting off topic as i usually do :)
 *
 * To Build/Run the program you will need visual studios . net on a windows machine
 * Or the IDE Rider EAP on a linux machine with dotnet installed on it. (should be similar for mac osx based computer as the linux ones)
 * However i haven't ever used macs all that much and obviously the programming .net languages are more geared for windows machines/visual stuido but can be pricey so if your
 * good with linux uses that.
 *
 *  The reason why i used C# is just purely for a change i been programming to much in c and java  i figure i go back to using .net for a while.
 *  But me personally java is much better then .net languages and .net languages mostly evolved from microsoft copying java programming language
 *  At any extent i recommend the student to rewrite this program in java its not hard to convert it and it would give you more experience as a programmer and understanding the math.
 *
 ______     ______     __    __     ______        ______   __  __     ______     ______     ______     __  __    
/\  ___\   /\  __ \   /\ "-./  \   /\  ___\      /\__  _\ /\ \_\ \   /\  ___\   /\  __ \   /\  == \   /\ \_\ \   
\ \ \__ \  \ \  __ \  \ \ \-./\ \  \ \  __\      \/_/\ \/ \ \  __ \  \ \  __\   \ \ \/\ \  \ \  __<   \ \____ \  
 \ \_____\  \ \_\ \_\  \ \_\ \ \_\  \ \_____\       \ \_\  \ \_\ \_\  \ \_____\  \ \_____\  \ \_\ \_\  \/\_____\ 
  \/_____/   \/_/\/_/   \/_/  \/_/   \/_____/        \/_/   \/_/\/_/   \/_____/   \/_____/   \/_/ /_/   \/_____/ 
                                                                                                                 
 ______     __  __        __   __     ______     ______   ______                                                 
/\  == \   /\ \_\ \      /\ "-.\ \   /\  __ \   /\__  _\ /\  ___\                                                
\ \  __<   \ \____ \     \ \ \-.  \  \ \  __ \  \/_/\ \/ \ \  __\                                                
 \ \_____\  \/\_____\     \ \_\\"\_\  \ \_\ \_\    \ \_\  \ \_____\                                              
  \/_____/   \/_____/      \/_/ \/_/   \/_/\/_/     \/_/   \/_____/                                              
                                                                                                                 


 *
 * 
 * 
 */
namespace GameTheorySolver
{
    class Program
    {
        static void Main(string[] args)
        {
            double[,] payoff = new double[,]  { { -4, 2 }, { -3, -4 }, { 5, 6 }, { 7, 8 } ,{ 3 , 5 } };    //{{9, 10}, {12, 8}}; // this was an unstable case currently it just prints the minimax and maximin values
            Console.WriteLine("$$$$$$$$$$$$$$$$$$$$$ Game Theory PayOff Matrix Solver $$$$$$$$$$$$ " );
            PrintGame(payoff) ;
            Console.WriteLine( "The Minimax " + minimax(payoff) );
            Console.WriteLine( "The Maximin " + maximin(payoff) );
            Console.WriteLine("Is Game Stable " + isStableGame(payoff)) ;
            
            double[,] smatrix = getStableGameStrategies(payoff);
            if( smatrix != null ) //null if its a unstable game for now!
            PrintStrategies( smatrix );


        }


        //prints the best strategies in matrix form for the two players 1 indicates that the player should play that strategy , 0 means he should not play 
        /*For example if the result printed
         * 0  0  
         * 0  1  
         * 0  0  
         * 1  0  
         *
         * means column player has optimal strategies {1,0} , {0,1} , {1,1}  the fact a one was in both columns means {1,1} you can pretty much alternate between the first and second strategies 
         * means row player has optimal strategies {0,0,0,1} , {0,1,0,0} note some times the player will have many equivalent optimal strategies sometimes only one strategy.
         *
         * for example if the result printed instead
         * 0  0  
         * 0  0  
         * 0  0  
         * 1  0
         *
         * means column player has optimal strategies {1,0} but not {0,1} or {1,1}  any more
         * means row player has only {0,0,0,1} optimal strategy not multiply optimal strategies 
         * 
         */
        static void PrintStrategies( double[,] sm )
        {
            Console.WriteLine("=========Strategies for Game =============") ;
            
            for (int i = 0; i < sm.GetLength(0); i++)
            {
                for (int j = 0; j < sm.GetLength(1); j++)
                {
                    Console.Write(sm[i,j] + "  ");
                }

                Console.WriteLine();
                
            }


        }

        //returns the matrix form of all the best/optimal strategies to play the game for each player
        static double[,] getStableGameStrategies(double [,] a )
        {
            
            int rowsize = a.GetLength(0);
            int colsize = a.GetLength(1);
            double[,] smatrix = new double[rowsize,colsize];

            double gamevalue = minimax(a);
            if (gamevalue != maximin(a))
            {
                return null;
            }
            
            for (int i = 0; i < a.GetLength(0); i++ )
            {
                for (int j = 0; j < a.GetLength(1); j++)
                {
                    if (a[i,j] == gamevalue)
                    {
                        smatrix[i, j] = 1;
                    }
                }
                    
            }

            return smatrix;

        }
        
        //Prints the games payoff matrix , in game theory  a payoff matrix is a matrix of all the weights/values  of each players strategies 
        static void PrintGame(double [,] a)
        {
            Console.WriteLine("Payoff matrix =============================");
            for (int i = 0; i < a.GetLength(0) ; i++ )
            {
                for (int j = 0; j < a.GetLength(1); j++)
                {
                    Console.Write( a[i,j] + "  ");
                }
                
                Console.WriteLine();
                
            }
                
            Console.WriteLine("===========================================");
            
        }
        
        
        //This function computes the minimax of the column strategies for the game with payoff matrix double a[]
        static double  minimax(double [,] a)
        {
            int rowsize = a.GetLength(0);
            int colsize = a.GetLength(1);
            double[] mim = new double[colsize];
            double tempmim = Double.NegativeInfinity ;

            for (int i = 0; i < colsize; i++)
            {
                tempmim = Double.NegativeInfinity;
                
                for (int j = 0; j < rowsize; j++)
                {
                    if (tempmim < a[j, i])
                    {
                        mim[i] = a[j, i];
                        tempmim = a[j, i];
                    }

                }

            }

            double minimaxresult =  mim[0] ;
            for (int k = 0; k < colsize ; k++)
            {
                if (minimaxresult > mim[k])
                    minimaxresult = mim[k];
                
                //         Console.WriteLine(mim[k]);
            }
            
            return minimaxresult;
        }
        
        //This function calculates the maximin of the row strategies for the game with a given payoff matrix double a[]
        static double  maximin(double [,] a )
        {
            int rowsize = a.GetLength(0);
            int colsize = a.GetLength(1);
            double[] mxm = new double[rowsize];
            double tempmxm = Double.PositiveInfinity;

            for (int i = 0; i < rowsize; i++)
            {

                tempmxm = Double.PositiveInfinity;
                for (int j = 0; j < colsize; j++)
                {
                    if (tempmxm > a[i, j])
                    {
                        mxm[i] = a[i, j];
                        tempmxm = a[i, j];
                    }

                }
                
            }

            double maximinresult =  mxm[0] ;
            for (int k = 0; k < rowsize ; k++)
            {
                if (maximinresult < mxm[k])
                {
                    maximinresult = mxm[k];
                }
                //     Console.WriteLine(mxm[k]);
            }
            
            return maximinresult;
        
        }

        //This function returns if the payoff matrix for a given game is stable aka has a saddle point 
        static bool isStableGame(double [,] payoffmatrix)
        {
            if( minimax(payoffmatrix) == maximin(payoffmatrix) )
                return true;

            return false;

        }

       
        
    }
}