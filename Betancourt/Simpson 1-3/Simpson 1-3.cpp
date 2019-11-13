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

 a=0.0;
 b=0.8;


 cout<<"Cantidad de segmentos: ";  cin>>N;

 division = N/2;
 residuo = N - (division*2);

 while(residuo!=0)
 {
  cout<<"\nLa cantidad de segmentos debe ser PAR";
  cout<<"Cantidad de segmentos: ";  cin>>N;
  division = N/2;
  residuo = N - (division*2);
 }

 h = (b - a)/ N;   cout<<"Paso = "<<h<<endl;

 suma = tabla(X, Y, N, a, h);   cout<<"Suma de las Y = "<<suma<<endl;

 integral = (h/3)*suma;   cout<<"Integral = "<<integral<<endl;

 cout<<"\n";  system("pause");
}


double tabla(double *X, double *Y, double N, double a, double h)
{
 int i , n;
 double sumaY, sumaYpares , sumaYimpares;
 double ta, tb, tc, td, te;
 cout.precision(10);

 sumaY = 0; sumaYpares = 0;   sumaYimpares=0;

 X[0]= a;

 //AQUI LE CAMELLAN A LA FUNCION
 ta=(0.2+(25*a));  tb = ((-200)*pow(a,2));  tc=(675*pow(a,3)); td=((-900)*pow(a,4)); te=(400*pow(a,5));
 Y[0]= ta+tb+tc+td+te;

 for(i=1; i<=N; i++)
 {
  X[i] = X[i-1]+ h;

  //AQUI LE CAMELLAN A LA FUNCION
  ta=(0.2+(25*X[i]));  tb = ((-200)*pow(X[i],2));  tc=(675*pow(X[i],3)); td=((-900)*pow(X[i],4)); te=(400*pow(X[i],5));
  Y[i]= ta+tb+tc+td+te;
 }


 for(i=1; i<N; i=i+2)  {   sumaYimpares = sumaYimpares + Y[i];  }
 for(i=2; i<N; i=i+2)  {   sumaYpares = sumaYpares + Y[i];  }

 sumaYimpares = 4*sumaYimpares;
 sumaYpares = 2*sumaYpares;

 n = N;

 sumaY = sumaYpares + sumaYimpares + Y[0] + Y[n];

 return sumaY;
}
