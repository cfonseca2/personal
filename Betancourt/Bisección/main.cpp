#include <iostream>
#include <stdlib.h>
#include <math.h>

using namespace std;
using std::cout;
using std::endl;

void lecturas(double* , double*, int*);
void biseccion(double , double, int);
double eval_funcion(double);
/****************************************************************************/
main()
{
 double va, vb;
 int vn;
 lecturas(&va , &vb , &vn);
 biseccion(va , vb, vn);
}
/******************************************************************************/

void lecturas(double *a , double *b, int *N)
{
 cout<<" \n- B I S E C C I O N -\n "<<endl;
 printf("Para calcular la raiz de una funcion de tipo log(x)\n");
 cout<<"\nDigite el extremo inferior (a, debe ser menor que b) : "; cin>>*a;
 cout<<"Digite el extremo superior (b, debe ser mayor que a) : "; cin>>*b;
 cout<<"Digite el numero de iteraciones (mas de 2 iteraciones) : "; cin>>*N;

 while(*a>=*b)
 {
  cout<<"El extremo inferior (a) debe ser MENOR que el extremo (b) "<<endl;
  cout<<"Digite el extremo inferior (a) : "; cin>>*a;
  cout<<"Digite el extremo superior (b) : "; cin>>*b;
 }

 while(*N<=2)
 {
  cout<<"La cantidad de iteraciones debe ser mayor a 2 (>2) "<<endl;
  cout<<"Digite el numero de iteraciones : "; cin>>*N;
 }
}
/****************************************************************************/
void biseccion(double a , double b, int N)
{
 double TOL=0.00001;
 int i = 1;
 double aux1=0.0, aux2=0.0;
 double  efa=0.0, efp = 0.0, er=0.0, Xanterior, Xnueva;

 cout.precision(10);
 //El Paso 1 fue inicializar i en 1.
 cout<<"\n"<<"n"<<"\t"<<"Raiz"<<"\t\t"<<"f(Raiz)"<<"\t\t\t"<<"Er";

 //Paso 2: calculo del primer punto medio y evaluacion
 Xanterior = (a + b)/2;
 efa = eval_funcion(a);
 efp = eval_funcion(Xanterior);
 aux1 = efa * efp;
 if(aux1>0){a = Xanterior;}  else {b = Xanterior;}

 //Paso 3:
 while(i <= N)
 {
   //Paso 4: Calculo y evaluacion de nueva raiz y calculo del error relativo
   Xnueva = (a + b)/2;    efp = eval_funcion(Xnueva);
   er = (Xnueva - Xanterior)/Xnueva;  if(er<0){er=er*(-1);}

   //Paso 5: Mostrar raiz aproximada
   if((efp==0)||(er<TOL))
   {
     cout<<"\n"<<i+1<<"\t"<<Xnueva<<"\t\t"<<efp<<"\t\t"<<er;
     system("pause"); exit(1);
   }

   //Paso 6: actualizar contador de iteraciones
   i = i + 1;

   //Adicional: Para mostrar resultados iteracion a iteracion
   cout<<"\n"<<i<<"\t"<<Xnueva<<"\t\t"<<efp<<"\t\t"<<er;

   //Paso 7:  re-definicion del intervalo y actualizacion de raiz
   efa = eval_funcion(a);
   aux1 = efa*efp;   //f(a)*f(Xnueva)
   if(aux1>0){a = Xnueva; }   else {b = Xnueva;}
   Xanterior = Xnueva; //Actualizacion de raiz
 }

 //el "paso 8"
 if((i>N)&&(er>TOL)) {cout<<"\nEl metodo ha concluido despues de "<<N<<" iteraciones";}

 cout<<"\n";  system("pause");
}
/****************************************************************************/

//PRIMERA

double eval_funcion(double v)
{
 double r=0.0 , ee=0.0, emx=0.0, logx=0.0;
 ee = pow(2.718281828,v); emx = 1/ee;
 logx = log(v);

 r = emx - logx;
 return r;
}


//SEGUNDA
/*
double eval_funcion(double v)
{
 double r=0.0;
 r = (v*v*v) + (4*v*v) - 10;
 return r;
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
*/

/*
//QUINTA
double eval_funcion(double v)
{
 double r=0.0;
 r = (v*v*v) - (2*v*v) - v + 1;
 return r;
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
*/
