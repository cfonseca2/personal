
#include <iostream>
#include <stdlib.h>
#include <math.h>

using namespace std;
using std::cout;
using std::endl;

void lecturas(double* , int*);
void newton(double , int);
double eval_funcion(double);
double eval_derfuncion(double);
/****************************************************************************/

main()
{
 double vpo;
 int vn = 0 ;
 lecturas(&vpo , &vn);
 newton(vpo , vn);
}
/*****************************************************************************/

void lecturas(double *po , int *N)
{
 
 cout<<"\n - N E W T O N    R A P H S O N- \n"<<endl;
 printf("Para calcular la raiz de una funcion del tipo ln(x)\n ");
 cout<<"\nDigite la aproximacion inicial : "; cin>>*po;
 cout<<"Digite el numero de iteraciones : "; cin>>*N;

 while(*N<=2)
 {
  cout<<"La cantidad de iteraciones debe ser positiva (>2) "<<endl;
  cout<<"Digite el numero de iteraciones : "; cin>>*N;
 }
}
/****************************************************************************/

void newton(double po, int N)
{
 double TOL=0.00001 , ef=0.0 , edf=0.0, Er=0.0 , p=0.0 , c=0.0 , efp=0.0;
 int i = 1 ; //el "paso 1"
 cout.precision(10);
 cout<<"\n"<<"\t-- po -- "<<"\t -- p -- "<<"\t -- f(p) -- "<<"\t-- Er -- ";

 //el "paso 2"
 while(i <= N)
 {
  ef = eval_funcion(po);
  edf = eval_derfuncion(po);
  c = ef / edf ; //   f(po) / f'(po)
  p = po - c;    //el "paso 3"
  efp = eval_funcion(p);
  Er = (p - po)/p;    if(Er<0) Er=Er*(-1); //esto es para ayudar al paso 4

  //Para mostrar resultados iteracion a iteracion
  cout<<"\n"<<i<<"\t"<<po<<"\t"<<p<<"\t"<<efp<<"\t"<<Er;

  //el "paso 4"
  if(Er<TOL) {cout<<"\n\nProcedimiento completado satisfactoriamente\n"; system("pause"); exit(1);}

  //el "paso 5"
  i = i + 1;

  //el "paso 6"  redefinicion de po
  po = p;
 }

 //el "paso 7"
 if((i>N)||(Er>TOL)) {cout<<"\nEl metodo concluyo despues de "<<N<<" iteraciones";}
 cout<<"\n"; system("pause");
}
/****************************************************************************/

//PRIMERA
double eval_funcion(double v)
{
 double r=0.0, x=0.0, ex=0.0 , emx=0.0 , lnx=0.0;
 ex = pow(2.718281828,v); emx=1/ex;
 lnx = log(v);
 r = emx - lnx;
 return r;
}

double eval_derfuncion(double z)
{
 double r=0.0, x=0.0, ex=0.0 , emx=0.0, memx=0.0;
 ex = pow(2.718281828,z); emx=1/ex; memx=(-1)*emx;
 r = memx - (1/z);
 return r;
}

/*
//SEGUNDA
double eval_funcion(double v)
{
 double r=0.0 , a1 = 0.0, a2 = 0.0;
 a1 = v*v*v;
 a2 = 4*v*v;
 r = a1 + a2 - 10;
 return r;
}

double eval_derfuncion(double z)
{
 double s=0.0, a1 = 0.0, a2 = 0.0;
 a1 = 3*z*z;
 a2 = 8*z;
 s = a1 + a2;
 return s;
}
*/

/*
//TERCERA
double eval_funcion(double v)
{
 double r=0.0 , ee =0.0, emx = 0.0;
 ee = pow(2.718281828,v); emx = 1/ee;
 r = emx + (v*v) - 2;
 return r;
}

double eval_derfuncion(double z)
{
 double r=0.0, x=0.0, ex=0.0 , emx=0.0, memx=0.0;
 ex = pow(2.718281828,z); emx=1/ex; memx=(-1)*emx;
 r = memx + (2*z);
 return r;
}
*/

/*
//CUARTA
double eval_funcion(double v)
{
 double r=0.0 , ee =0.0, emx = 0.0;
 ee = pow(2.718281828,v); emx = 1/ee;
 r = (2*emx) - sin(v);
 return r;
}

double eval_derfuncion(double z)
{
 double r=0.0, x=0.0, ex=0.0 , emx=0.0, memx=0.0;
 ex = pow(2.718281828,z); emx=1/ex; memx=(-1)*emx;
 r = 2*memx - cos(z);
 return r;
}
*/

/*
//QUINTA
double eval_funcion(double v)
{
 double r=0.0;
 r = (v*v*v) - (2*v*v) - v + 1;
 return r;
}

double eval_derfuncion(double z)
{
 double s=0.0, a1 = 0.0, a2 = 0.0;
 a1 = 3*z*z;
 a2 = 4*z;
 s = a1 - a2 - 1;
 return s;
}
*/


/*
//CASO ESPECIAL
double eval_funcion(double v)
{
 double r=0.0 ,
 xd = pow(v,10);
 r = xd - 1;
 return r;
}

double eval_derfuncion(double z)
{
 double r=0.0, xd;
 xd = pow(z,9);
 r = 10 * xd;
 return r;
}
*/
