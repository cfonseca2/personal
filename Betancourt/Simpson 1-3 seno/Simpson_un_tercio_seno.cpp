#include <iostream>
#include <stdlib.h>
#include <math.h>

using namespace std;
using std::cout;
using std::endl;

double tabla(double * , double *, double, double, double);

main()
{
 double X[30000]= {0};
 double Y[30000]= {0};
 int i,D;
 double a , b, N, h , suma, integral;
 int division, residuo;
 cout.precision(10);

  //a=0.0;          //Limite inferior de la integral
 a=0;          //Limite inferior de la integral. //aproximacion de  pi/2
 b=0;   //aproximacion de pi
 //b=4.71238898038469;   //aproximacion de (3*pi)/2
 //b=6.283185308;   //aproximacion de (2*pi)

 printf("\n-- Este metodo te permite integrar funciones del tipo sen(x)dx --\n");
 cout<<"\nCantidad de segmentos (debe ser un numero par): ";  cin>>N;
 cout<<"\nLimite inferior de la integral: "; cin>>a;
 cout<<"\nLimite superior de la integral (debe ser mayor al limite inferior): "; cin>>b;

 division = N/2;
 residuo = N - (division*2);

 while(residuo!=0)
 {
  cout<<"\nLa cantidad de segmentos debe ser PAR\n";
  cout<<"\nCantidad de segmentos: ";  cin>>N;
  division = N/2;
  residuo = N - (division*2);
 }


 h = (b - a)/ N;   cout<<"\nTamano del intervalo = "<<h<<endl;

 suma = tabla(X, Y, N, a, h);   cout<<"Suma de las Y = "<<suma<<endl;

 integral = (h/3)*suma;   cout<<"Integral = "<<integral<<endl;

 cout<<"\n";  system("pause");
}


double tabla(double *X, double *Y, double N, double a, double h)
{
 int i , n;
 double aux, sumaY, sumaYpares , sumaYimpares;

 sumaY = 0; sumaYpares = 0;   sumaYimpares=0;

 X[0]= a;

 Y[0]= sin(a);  //AQUI LE CAMELLAN A LA FUNCION

 for(i=1; i<=N; i++)
 {
  X[i] = X[i-1]+ h;

  Y[i]= sin(X[i]);  if(Y[i]<0) Y[i]=Y[i]*(-1);//AQUI LE CAMELLAN A LA FUNCION
 }


 for(i=1; i<N; i=i+2)  {   sumaYimpares = sumaYimpares + Y[i];  }
 for(i=2; i<N; i=i+2)  {   sumaYpares = sumaYpares + Y[i];  }

 sumaYimpares = 4*sumaYimpares;
 sumaYpares = 2*sumaYpares;

 n = N;

 sumaY = sumaYpares + sumaYimpares + Y[0] + Y[n];

 return sumaY;
}
