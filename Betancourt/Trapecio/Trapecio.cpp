#include <iostream>
#include <stdlib.h>
#include <math.h>

using namespace std;
using std::cout;
using std::endl;

double tabla(double * , double *, double, double, double);

int main()
{
 double X[50000]= {0};
 double Y[50000]= {0};
 double a , b, N, h , suma, integral;
 cout.precision(10);

 a=0;          //Limite inferior de la integral
 b=0;

 printf("\n--- METODO DE INTEGRACION NUMERICA DEL TRAPECIO ---\n");
 printf("\nEste programa te permite calcular la integral de la funcion del tipo 1/x dx \n");
 cout<<"\nCantidad de segmentos: ";  cin>>N;
 cout<<"\nLimite inferior de la integral: "; cin>>a;
 cout<<"\nLimite superior de la integral (debe ser mayor al inferior): "; cin>>b;

 h = (b - a)/ N;   cout<<"\nMedida de intervalos = "<<h<<endl;

 suma = tabla(X, Y, N, a, h);   cout<<"Suma de las Y = "<<suma<<endl;

 integral = ((b-a)/(2*N))*suma;   cout<<"Integral = "<<integral<<endl;

 cout<<"\n";  system("pause");
}


double tabla(double *X, double *Y, double N, double a, double h)
{
 int i , n;
 double sumaY;

 sumaY = 0;   X[0]= a;

 Y[0]= (1/a);  //AQUI LE CAMELLAN A LA FUNCION


 for(i=1; i<=N; i++)
 {
  X[i] = X[i-1]+ h;
  Y[i]= (1/X[i]);  if(Y[i]<0) Y[i]=Y[i]*(-1);//AQUI LE CAMELLAN A LA FUNCION
 }


 for(i=1; i<N; i++)  {   sumaY = sumaY + Y[i];  }

 sumaY = 2*sumaY;  n = N;

 sumaY = sumaY + Y[0] + Y[n];

 return sumaY;
}
