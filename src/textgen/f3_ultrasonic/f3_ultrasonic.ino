
#include <Arduino.h>
#include <debug.h>
#include <ultraschall.h>
//----------------- Ultraschall -----------------------
//                   3x HC-SR04
//sensor#0:right
#define US1_ECHO      PE11
#define US1_TRIGGER   PE10
//sensor#1:front
#define US2_ECHO      PF6
#define US2_TRIGGER   PA12//PD0
//sensor#2:left
#define US3_ECHO      PA13//PF9
#define US3_TRIGGER   PA11//PB0



void UsHandler(int id, int entfernung) // entfernung : distance
{
	Print2Number("Sensor", id, entfernung);
}


void setup() 
{
	delay(150);
	debugInit();
	debugSetTimeStamp(DDEBUG_TIME_STAMP_ON);

	// Ultraschallsensoren definieren
	addUSSensor(0, US1_TRIGGER, US1_ECHO, 220);
	addUSSensor(1, US2_TRIGGER, US2_ECHO, 220);
	addUSSensor(2, US3_TRIGGER, US3_ECHO);

	UsAttachHandler(0, UsHandler);
	UsSetMessPause(100);  
  //
  //Pause of 100 ms after each measurement
  //So that the output in the terminal does not become too fast
  //In the normal case no so long pause would be programmed.
  //The break is only necessary if it is determined that
  //The sensors mutually influence each other by subsequent multiple echoes.
  //This is environment-dependent.

	debugPrint("activate Sensor 0 ---------------");
	aktiviereUSSensor(0);
	PrintUsSensorList();
	debugPrint("activate Sensor 1 ---------------");
	aktiviereUSSensor(1);
	PrintUsSensorList();

	debugPrint("activate Sensor 2 ---------------");
	aktiviereUSSensor(2);
	PrintUsSensorList();

	// ab jetzt wird im Hintergrund gemessen : From now on is measured in the background

	delay(1000);
	debugPrint("deactivate Sensor 2 ---------------");
	deaktiviereUSSensor(2);
	PrintUsSensorList();

	delay(1000);
	debugPrint("deactivate Sensor 1 ---------------");
	deaktiviereUSSensor(1);
	PrintUsSensorList();

	delay(1000);
	debugPrint("deactivate Sensor 0 ---------------");
	deaktiviereUSSensor(0);
	PrintUsSensorList();

	delay(1000);
	debugPrint("activate Sensor 0 ---------------");
	aktiviereUSSensor(0);
	PrintUsSensorList();

	delay(1000);
    debugPrint("activate Sensor 1 ---------------");
	aktiviereUSSensor(1);
	PrintUsSensorList();


	debugPrint("Programm gestartet");
}

void loop()
{
	Print2Number("Sensor 1", UsLetzterGueltigerMesswert(1), UsLetzterGueltigerMesswertAlter(1));
    delay(300);

}




