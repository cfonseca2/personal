#include <iostream>
#include <cstdlib>
 
using namespace std;
 
void pausa();
 
int main()
{
    bool bandera=false;
    char tecla;
 
    do
    {
        system("cls");
        cin.clear();
        cout << "\n\nProyecto final de METODOS NUMERICOS" << endl;
        printf("\n\nElaboraron");
 		printf("\nFonseca Guizar Cesar Alfonso");
 		printf("\nGutierrez Molina Brandom Ricardo\n");
 		
        cout << "--------------------------------------" << endl << endl;
        cout << "\t1 .- METODO DE BISECCION" << endl;
        cout << "\t2 .- METODO DE NEWTON RAPHSON" << endl;
        cout << "\t3 .- METODO DEL TRAPECIO" << endl;
        cout << "\t4 .- METODO DE SIMPSON 1/3" << endl;
        cout << "\t5 .- Salir" << endl << endl;
        cout << "\nElije una opcion: ";
       	cin >> tecla;
       	
	    
        
 
		switch(tecla)
		{
			case '1':
				system("cls");
				cout << "Has elegido METODO DE BISECCION.\n";
				system("Biseccion.exe");
				pausa();
				break;
 
			case '2':
				system("cls");
				cout << "Has elegido METODO DE NEWTON RAPHSON.\n";
				system("Newton_Raphson.exe");
				pausa();
				break;
 
			case '3':
				system("cls");
				cout << "Has elegido METODO DEL TRAPECIO.\n";
				system("Trapecio.exe");
				pausa();
				break;
 
			case '4':
				system("cls");
				cout << "Has elegido METODO DE SIMPSON 1/3.\n";
				system("Simpson_un_tercio_seno.exe");
				pausa();
				break;
 
			case '5':
				bandera=true;
				//exit(1);
				break;
 
			default:
				system("cls");
				cout << "Opcion no valida.\a\n";
				pausa();
				break;
		}
    }while(bandera!=true);
 
    return 0;
}
 
void pausa()
{
    cout << "Pulsa una tecla para continuar...";
    getwchar();
    getwchar();
}
