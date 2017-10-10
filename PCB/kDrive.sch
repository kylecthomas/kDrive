EESchema Schematic File Version 2
LIBS:power
LIBS:device
LIBS:transistors
LIBS:conn
LIBS:linear
LIBS:regul
LIBS:74xx
LIBS:cmos4000
LIBS:adc-dac
LIBS:memory
LIBS:xilinx
LIBS:microcontrollers
LIBS:dsp
LIBS:microchip
LIBS:analog_switches
LIBS:motorola
LIBS:texas
LIBS:intel
LIBS:audio
LIBS:interface
LIBS:digital-audio
LIBS:philips
LIBS:display
LIBS:cypress
LIBS:siliconi
LIBS:opto
LIBS:atmel
LIBS:contrib
LIBS:valves
LIBS:kDrive
LIBS:kDrive-cache
EELAYER 25 0
EELAYER END
$Descr A4 11693 8268
encoding utf-8
Sheet 1 1
Title ""
Date ""
Rev ""
Comp ""
Comment1 ""
Comment2 ""
Comment3 ""
Comment4 ""
$EndDescr
$Comp
L Battery BT1
U 1 1 56A717BF
P 800 2100
F 0 "BT1" H 900 2150 50  0000 L CNN
F 1 "Battery" H 900 2050 50  0000 L CNN
F 2 "kDrive:Keystone_3003_CR2032" V 800 2140 50  0001 C CNN
F 3 "" V 800 2140 50  0000 C CNN
F 4 "36-3003-ND" H 800 2100 60  0001 C CNN "digikey#"
F 5 "534-3003" H 800 2100 60  0001 C CNN "mouser#"
	1    800  2100
	1    0    0    -1  
$EndComp
$Comp
L SW_PUSH SW1
U 1 1 56A719D5
P 3100 5350
F 0 "SW1" H 3250 5460 50  0000 C CNN
F 1 "SW_PUSH" H 3100 5270 50  0000 C CNN
F 2 "kDrive:Cherry_MX1A-C1NW" H 3100 5350 50  0001 C CNN
F 3 "" H 3100 5350 50  0000 C CNN
F 4 "MX1A-C1NW" H 3100 5350 60  0001 C CNN "manf#"
F 5 "540-MX1A-C1NW" H 3100 5350 60  0001 C CNN "mouser#"
F 6 "CH310-ND" H 3100 5350 60  0001 C CNN "digikey#"
	1    3100 5350
	0    -1   -1   0   
$EndComp
$Comp
L R R8
U 1 1 57D4BB8C
P 6400 4600
F 0 "R8" V 6480 4600 50  0000 C CIN
F 1 "10K" V 6400 4600 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 6330 4600 50  0001 C CNN
F 3 "" H 6400 4600 50  0000 C CNN
F 4 "RMCF0805JT10K0" H 6400 4600 60  0001 C CNN "manf#"
	1    6400 4600
	1    0    0    -1  
$EndComp
$Comp
L R R4
U 1 1 57D4C492
P 3100 4550
F 0 "R4" V 3180 4550 50  0000 C CNN
F 1 "10K" V 3100 4550 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 3030 4550 50  0001 C CNN
F 3 "" H 3100 4550 50  0000 C CNN
F 4 "RMCF0805JT10K0" H 3100 4550 60  0001 C CNN "manf#"
	1    3100 4550
	1    0    0    -1  
$EndComp
$Comp
L C C3
U 1 1 57D4C552
P 3500 5350
F 0 "C3" H 3525 5450 50  0000 L CNN
F 1 ".1u" H 3525 5250 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 3538 5200 50  0001 C CNN
F 3 "" H 3500 5350 50  0000 C CNN
F 4 "08055C104KAT2A" H 3500 5350 60  0001 C CNN "manf#"
	1    3500 5350
	1    0    0    -1  
$EndComp
Text Label 3500 4900 3    60   ~ 0
Bleft
Text Label 4750 4400 1    60   ~ 0
Bup
Text Label 4450 5550 1    60   ~ 0
Bdown
Text Label 5800 5150 1    60   ~ 0
Bright
Text Label 6900 5000 1    60   ~ 0
B1
Text Label 7850 5000 1    60   ~ 0
B2
Text Label 8850 5000 1    60   ~ 0
B3
$Comp
L GND #PWR01
U 1 1 57D4E85D
P 10000 6000
F 0 "#PWR01" H 10000 5750 50  0001 C CNN
F 1 "GND" H 10000 5850 50  0000 C CNN
F 2 "" H 10000 6000 50  0000 C CNN
F 3 "" H 10000 6000 50  0000 C CNN
	1    10000 6000
	1    0    0    -1  
$EndComp
$Comp
L GND #PWR02
U 1 1 57D4F03F
P 9450 3100
F 0 "#PWR02" H 9450 2850 50  0001 C CNN
F 1 "GND" H 9450 2950 50  0000 C CNN
F 2 "" H 9450 3100 50  0000 C CNN
F 3 "" H 9450 3100 50  0000 C CNN
	1    9450 3100
	1    0    0    -1  
$EndComp
Text Label 9850 5000 1    60   ~ 0
B4
$Comp
L CONN_02X10 Debug1
U 1 1 580D0F26
P 3800 1200
F 0 "Debug1" H 3800 1750 50  0000 C CNN
F 1 "CONN_02X10" V 3800 1200 50  0000 C CNN
F 2 "kDrive:GRPB102VWQS-RC" H 3800 0   50  0001 C CNN
F 3 "" H 3800 0   50  0000 C CNN
F 4 "3220-20-0300-00" H 3800 1200 60  0001 C CNN "manf#"
	1    3800 1200
	1    0    0    -1  
$EndComp
$Comp
L CONN_02X10 Simplicity1
U 1 1 580D0F85
P 5200 1200
F 0 "Simplicity1" H 5200 1750 50  0000 C CNN
F 1 "CONN_02X10" V 5200 1200 50  0000 C CNN
F 2 "kDrive:GRPB102VWQS-RC" H 5200 0   50  0001 C CNN
F 3 "" H 5200 0   50  0000 C CNN
F 4 "3220-20-0300-00" H 5200 1200 60  0001 C CNN "manf#"
	1    5200 1200
	1    0    0    -1  
$EndComp
Text Label 9000 2300 0    30   ~ 0
SWCLK
Text Label 9000 2250 0    30   ~ 0
SWDIO
Text Label 9000 2200 0    30   ~ 0
Bup
Text Label 9000 2150 0    30   ~ 0
Bdown
Text Label 9000 2100 0    30   ~ 0
Bleft
Text Label 9000 2050 0    30   ~ 0
Bright
Text Label 9000 2000 0    30   ~ 0
B1
Text Label 9000 1950 0    30   ~ 0
B2
Text Label 8050 1850 0    30   ~ 0
B3
Text Label 8050 1900 0    30   ~ 0
B4
Text Label 4100 750  0    30   ~ 0
SWDIO
Text Label 4100 850  0    30   ~ 0
SWCLK
Text Label 4800 750  0    30   ~ 0
VMCU
Text Label 4800 850  0    30   ~ 0
VDD3V
Text Label 5500 750  0    30   ~ 0
VCOM_TX
Text Label 5500 850  0    30   ~ 0
VCOM_RX
$Comp
L GND #PWR03
U 1 1 580F15DB
P 800 2400
F 0 "#PWR03" H 800 2150 50  0001 C CNN
F 1 "GND" H 800 2250 50  0000 C CNN
F 2 "" H 800 2400 50  0000 C CNN
F 3 "" H 800 2400 50  0000 C CNN
	1    800  2400
	1    0    0    -1  
$EndComp
Text Label 1550 2450 0    30   ~ 0
VMCU
Text Label 1950 1800 0    30   ~ 0
VDD3V
Text Label 4100 1150 0    30   ~ 0
RESET
Text Label 9000 1850 0    30   ~ 0
RESET
Text Label 2050 2400 0    60   ~ 0
VDD_BGM
Text Label 9000 1900 0    30   ~ 0
VDD_BGM
$Comp
L CONN_02X05 P1
U 1 1 587A7D13
P 6700 1500
F 0 "P1" H 6700 1800 50  0000 C CNN
F 1 "CONN_02X05" H 6700 1200 50  0000 C CNN
F 2 "kDrive:DIP-5x2_100mil" H 6700 300 50  0001 C CNN
F 3 "" H 6700 300 50  0000 C CNN
	1    6700 1500
	1    0    0    -1  
$EndComp
Text Label 1500 6300 0    30   ~ 0
CNFG_DATA
Text Label 1500 6200 0    30   ~ 0
CNFG_CLK
Text Label 1500 5900 0    30   ~ 0
VDD_BGM
Text Label 1500 5800 0    30   ~ 0
GND
$Comp
L Touch_Slider U1
U 1 1 587AC5D5
P 1500 4800
F 0 "U1" H 1000 4700 30  0000 C CNN
F 1 "Touch_Slider" H 1500 4700 60  0000 C CNN
F 2 "kDrive:Touch_Slider" H 1500 4800 30  0001 C CNN
F 3 "" H 1500 4800 30  0001 C CNN
	1    1500 4800
	-1   0    0    1   
$EndComp
$Comp
L CPT112S_QSOP U2
U 1 1 587AD6DB
P 1850 6500
F 0 "U2" H 2100 6600 60  0000 C CNN
F 1 "CPT112S_QSOP" V 2100 7250 60  0000 C CNN
F 2 "kDrive:CPT112_TSOP" H 2100 6900 60  0001 C CNN
F 3 "" H 2100 6900 60  0001 C CNN
F 4 "CPT112S-A02-GU" H 1850 6500 60  0001 C CNN "manf#"
	1    1850 6500
	1    0    0    -1  
$EndComp
Text Label 1700 6500 2    30   ~ 0
I2C_SCL
Text Label 1700 6400 2    30   ~ 0
I2C_INT
Text Label 2650 6400 2    30   ~ 0
I2C_SDA
Text Label 2100 4900 3    30   ~ 0
SLIDE_1
Text Label 1800 4900 3    30   ~ 0
SLIDE_2
Text Label 1500 4900 3    30   ~ 0
SLIDE_3
Text Label 1200 4900 3    30   ~ 0
SLIDE_4
Text Label 900  4900 3    30   ~ 0
SLIDE_5
$Comp
L C C1
U 1 1 587B09C2
P 800 5850
F 0 "C1" H 825 5950 50  0000 L CNN
F 1 "0.1u" H 825 5750 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 838 5700 50  0001 C CNN
F 3 "" H 800 5850 50  0000 C CNN
F 4 "08055C104KAT2A" H 800 5850 60  0001 C CNN "manf#"
	1    800  5850
	1    0    0    -1  
$EndComp
$Comp
L C C2
U 1 1 587B0A37
P 1050 5850
F 0 "C2" H 1075 5950 50  0000 L CNN
F 1 "4.7u" H 1075 5750 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 1088 5700 50  0001 C CNN
F 3 "" H 1050 5850 50  0000 C CNN
F 4 "GRM21BR61E475KA12L" H 1050 5850 60  0001 C CNN "manf#"
	1    1050 5850
	1    0    0    -1  
$EndComp
$Comp
L R R2
U 1 1 587B1151
P 1500 6650
F 0 "R2" V 1580 6650 50  0000 C CNN
F 1 "2K" V 1500 6650 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 1430 6650 50  0001 C CNN
F 3 "" H 1500 6650 50  0000 C CNN
F 4 "RC0805FR-072KL" H 1500 6650 60  0001 C CNN "manf#"
	1    1500 6650
	1    0    0    -1  
$EndComp
$Comp
L R R3
U 1 1 587B13EF
P 2700 6650
F 0 "R3" V 2780 6650 50  0000 C CNN
F 1 "2K" V 2700 6650 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 2630 6650 50  0001 C CNN
F 3 "" H 2700 6650 50  0000 C CNN
F 4 "RC0805FR-072KL" H 2700 6650 60  0001 C CNN "manf#"
	1    2700 6650
	1    0    0    -1  
$EndComp
Text Label 1500 6850 3    30   ~ 0
VDD_BGM
Text Label 2700 6850 3    30   ~ 0
VDD_BGM
Text Label 6400 1600 2    30   ~ 0
CNFG_CLK
Text Label 7200 1400 2    30   ~ 0
CNFG_DATA
Text Label 6350 1400 2    30   ~ 0
GND
Text Label 6350 1700 2    30   ~ 0
GND
Text Label 7100 1300 2    30   ~ 0
GND
$Comp
L R R1
U 1 1 587DAB7D
P 1350 6200
F 0 "R1" V 1430 6200 50  0000 C CNN
F 1 "1K" V 1350 6200 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 1280 6200 50  0001 C CNN
F 3 "" H 1350 6200 50  0000 C CNN
F 4 "RMCF0805JT1K00" H 1350 6200 60  0001 C CNN "manf#"
	1    1350 6200
	0    1    1    0   
$EndComp
Text Label 1100 6200 2    30   ~ 0
VDD_BGM
Text Label 8650 2700 3    30   ~ 0
I2C_SDA
$Comp
L BGM111 U3
U 1 1 587DB336
P 8250 2550
F 0 "U3" H 8550 2950 60  0000 C CNN
F 1 "BGM111" H 8550 3350 50  0000 C CNN
F 2 "kDrive:BGM111" H 8550 2900 60  0001 C CNN
F 3 "" H 8550 2900 60  0000 C CNN
F 4 "BGM111A256V2" H 8250 2550 60  0001 C CNN "manf#"
	1    8250 2550
	1    0    0    -1  
$EndComp
Text Label 8700 2700 3    30   ~ 0
I2C_SCL
Text Label 3400 1450 0    30   ~ 0
GND
Text Label 3400 1550 0    30   ~ 0
GND
Text Label 3400 1650 0    30   ~ 0
GND
Text Label 3400 850  0    30   ~ 0
GND
Text Label 3400 950  0    30   ~ 0
GND
Text Label 3400 750  0    30   ~ 0
VDD_BGM
Text Label 4800 1050 0    30   ~ 0
GND
Text Label 4800 1150 0    30   ~ 0
GND
Text Label 4800 1250 0    30   ~ 0
GND
Text Label 4800 1350 0    30   ~ 0
GND
Text Label 4800 1450 0    30   ~ 0
GND
Text Label 8650 3700 0    60   ~ 0
VDD_BGM
$Comp
L R R14
U 1 1 58845D41
P 9150 1500
F 0 "R14" V 9230 1500 50  0000 C CNN
F 1 "10K" V 9150 1500 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 9080 1500 50  0001 C CNN
F 3 "" H 9150 1500 50  0000 C CNN
F 4 "RMCF0805JT10K0" H 9150 1500 60  0001 C CNN "manf#"
	1    9150 1500
	1    0    0    -1  
$EndComp
Text Label 9150 1350 1    30   ~ 0
VDD_BGM
$Comp
L LED D3
U 1 1 58865DC3
P 7400 2900
F 0 "D3" H 7400 3000 50  0000 C CNN
F 1 "LED" H 7400 2800 50  0000 C CNN
F 2 "kDrive:OVS5MxBCR4" H 7400 2900 50  0001 C CNN
F 3 "" H 7400 2900 50  0000 C CNN
F 4 "OVS5MBBCR4" H 7400 2900 60  0001 C CNN "manf#"
	1    7400 2900
	0    -1   -1   0   
$EndComp
$Comp
L R R11
U 1 1 58865F0D
P 7400 2450
F 0 "R11" V 7480 2450 50  0000 C CNN
F 1 "R" V 7400 2450 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 7330 2450 50  0001 C CNN
F 3 "" H 7400 2450 50  0000 C CNN
	1    7400 2450
	1    0    0    -1  
$EndComp
$Comp
L R R10
U 1 1 5886628F
P 7050 2450
F 0 "R10" V 7130 2450 50  0000 C CNN
F 1 "R" V 7050 2450 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 6980 2450 50  0001 C CNN
F 3 "" H 7050 2450 50  0000 C CNN
	1    7050 2450
	1    0    0    -1  
$EndComp
$Comp
L LED D2
U 1 1 5886632C
P 7050 2900
F 0 "D2" H 7050 3000 50  0000 C CNN
F 1 "LED" H 7050 2800 50  0000 C CNN
F 2 "kDrive:OVS5MxBCR4" H 7050 2900 50  0001 C CNN
F 3 "" H 7050 2900 50  0000 C CNN
F 4 "OVS5MYBCR4" H 7050 2900 60  0001 C CNN "manf#"
	1    7050 2900
	0    -1   -1   0   
$EndComp
$Comp
L R R9
U 1 1 58866892
P 6700 2450
F 0 "R9" V 6780 2450 50  0000 C CNN
F 1 "R" V 6700 2450 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 6630 2450 50  0001 C CNN
F 3 "" H 6700 2450 50  0000 C CNN
	1    6700 2450
	1    0    0    -1  
$EndComp
$Comp
L LED D1
U 1 1 58866933
P 6700 2900
F 0 "D1" H 6700 3000 50  0000 C CNN
F 1 "LED" H 6700 2800 50  0000 C CNN
F 2 "kDrive:OVS5MxBCR4" H 6700 2900 50  0001 C CNN
F 3 "" H 6700 2900 50  0000 C CNN
F 4 "OVS5MRBCR4" H 6700 2900 60  0001 C CNN "manf#"
	1    6700 2900
	0    -1   -1   0   
$EndComp
Text Label 5500 1050 0    30   ~ 0
VCOM_RTS
Text Label 5500 950  0    30   ~ 0
VCOM_CTS
Text Label 7950 2000 0    30   ~ 0
VCOM_TX
Text Label 7950 2050 0    30   ~ 0
VCOM_RX
Text Label 7950 2100 0    30   ~ 0
VCOM_CTS
Text Label 7950 2150 0    30   ~ 0
VCOM_RTS
Text Label 6700 2250 1    30   ~ 0
LED0
Text Label 7050 2250 1    30   ~ 0
LED1
Text Label 7400 2250 1    30   ~ 0
LED2
Text Label 8450 2800 1    30   ~ 0
LED0
Text Label 8500 2800 1    30   ~ 0
LED1
Text Label 8550 2800 1    30   ~ 0
LED2
$Comp
L C C11
U 1 1 58853A92
P 10150 1850
F 0 "C11" H 10175 1950 50  0000 L CNN
F 1 "100n" H 10175 1750 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 10188 1700 50  0001 C CNN
F 3 "" H 10150 1850 50  0000 C CNN
F 4 "08055C104KAT2A" H 10150 1850 60  0001 C CNN "manf#"
	1    10150 1850
	1    0    0    -1  
$EndComp
$Comp
L C C12
U 1 1 58853B0D
P 10450 1850
F 0 "C12" H 10475 1950 50  0000 L CNN
F 1 "100n" H 10475 1750 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 10488 1700 50  0001 C CNN
F 3 "" H 10450 1850 50  0000 C CNN
F 4 "08055C104KAT2A" H 10450 1850 60  0001 C CNN "manf#"
	1    10450 1850
	1    0    0    -1  
$EndComp
$Comp
L C C13
U 1 1 58853B82
P 10750 1850
F 0 "C13" H 10775 1950 50  0000 L CNN
F 1 "10u" H 10775 1750 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 10788 1700 50  0001 C CNN
F 3 "" H 10750 1850 50  0000 C CNN
F 4 "GRM21BR60J106ME19L" H 10750 1850 60  0001 C CNN "manf#"
	1    10750 1850
	1    0    0    -1  
$EndComp
Text Label 10150 1600 0    60   ~ 0
VDD_BGM
Text Label 10150 2100 0    60   ~ 0
GND
Text Label 1550 5500 0    30   ~ 0
SLIDE_5
Text Label 1550 5400 0    30   ~ 0
SLIDE_4
Text Label 2500 5400 0    30   ~ 0
SLIDE_3
Text Label 2500 5500 0    30   ~ 0
SLIDE_2
Text Label 2500 5600 0    30   ~ 0
SLIDE_1
Text Label 8600 2850 1    30   ~ 0
I2C_INT
Text Label 1500 4050 1    60   ~ 0
GND
$Comp
L copper_fill U4
U 1 1 58965BC3
P 1500 3700
F 0 "U4" H 1750 3450 60  0000 C CNN
F 1 "copper_fill" H 1750 4000 60  0000 C CNN
F 2 "kDrive:copper_fill" H 1500 3700 60  0001 C CNN
F 3 "" H 1500 3700 60  0001 C CNN
	1    1500 3700
	0    -1   -1   0   
$EndComp
$Comp
L R R16
U 1 1 5897B70C
P 3300 4800
F 0 "R16" V 3380 4800 50  0000 C CNN
F 1 "10K" V 3300 4800 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 3230 4800 50  0001 C CNN
F 3 "" H 3300 4800 50  0000 C CNN
F 4 "RMCF0805JT10K0" H 3300 4800 60  0001 C CNN "manf#"
	1    3300 4800
	0    1    1    0   
$EndComp
$Comp
L R R17
U 1 1 58993C29
P 4300 5300
F 0 "R17" V 4380 5300 50  0000 C CNN
F 1 "10K" V 4300 5300 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 4230 5300 50  0001 C CNN
F 3 "" H 4300 5300 50  0000 C CNN
F 4 "RMCF0805JT10K0" H 4300 5300 60  0001 C CNN "manf#"
	1    4300 5300
	0    1    1    0   
$EndComp
$Comp
L R R6
U 1 1 58994576
P 4100 5050
F 0 "R6" V 4180 5050 50  0000 C CNN
F 1 "10K" V 4100 5050 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 4030 5050 50  0001 C CNN
F 3 "" H 4100 5050 50  0000 C CNN
F 4 "RMCF0805JT10K0" H 4100 5050 60  0001 C CNN "manf#"
	1    4100 5050
	-1   0    0    1   
$EndComp
$Comp
L R R5
U 1 1 58994BB9
P 4300 3950
F 0 "R5" V 4380 3950 50  0000 C CNN
F 1 "10K" V 4300 3950 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 4230 3950 50  0001 C CNN
F 3 "" H 4300 3950 50  0000 C CNN
F 4 "RMCF0805JT10K0" H 4300 3950 60  0001 C CNN "manf#"
	1    4300 3950
	-1   0    0    1   
$EndComp
$Comp
L R R18
U 1 1 589953B3
P 4550 4150
F 0 "R18" V 4630 4150 50  0000 C CNN
F 1 "10K" V 4550 4150 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 4480 4150 50  0001 C CNN
F 3 "" H 4550 4150 50  0000 C CNN
F 4 "RMCF0805JT10K0" H 4550 4150 60  0001 C CNN "manf#"
	1    4550 4150
	0    -1   -1   0   
$EndComp
$Comp
L R R7
U 1 1 58996E11
P 5350 4550
F 0 "R7" V 5430 4550 50  0000 C CNN
F 1 "10K" V 5350 4550 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 5280 4550 50  0001 C CNN
F 3 "" H 5350 4550 50  0000 C CNN
F 4 "RMCF0805JT10K0" H 5350 4550 60  0001 C CNN "manf#"
	1    5350 4550
	-1   0    0    1   
$EndComp
$Comp
L R R19
U 1 1 58996F62
P 5600 4800
F 0 "R19" V 5680 4800 50  0000 C CNN
F 1 "10K" V 5600 4800 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 5530 4800 50  0001 C CNN
F 3 "" H 5600 4800 50  0000 C CNN
F 4 "RMCF0805JT10K0" H 5600 4800 60  0001 C CNN "manf#"
	1    5600 4800
	0    -1   -1   0   
$EndComp
$Comp
L R R20
U 1 1 58997F3D
P 6650 4800
F 0 "R20" V 6730 4800 50  0000 C CIN
F 1 "10K" V 6650 4800 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 6580 4800 50  0001 C CNN
F 3 "" H 6650 4800 50  0000 C CNN
F 4 "RMCF0805JT10K0" H 6650 4800 60  0001 C CNN "manf#"
	1    6650 4800
	0    1    1    0   
$EndComp
$Comp
L R R12
U 1 1 58998EDF
P 7400 4600
F 0 "R12" V 7480 4600 50  0000 C CIN
F 1 "10K" V 7400 4600 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 7330 4600 50  0001 C CNN
F 3 "" H 7400 4600 50  0000 C CNN
F 4 "RMCF0805JT10K0" H 7400 4600 60  0001 C CNN "manf#"
	1    7400 4600
	1    0    0    -1  
$EndComp
$Comp
L R R21
U 1 1 5899912A
P 7650 4800
F 0 "R21" V 7730 4800 50  0000 C CIN
F 1 "10K" V 7650 4800 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 7580 4800 50  0001 C CNN
F 3 "" H 7650 4800 50  0000 C CNN
F 4 "RMCF0805JT10K0" H 7650 4800 60  0001 C CNN "manf#"
	1    7650 4800
	0    1    1    0   
$EndComp
$Comp
L R R13
U 1 1 58999F10
P 8400 4600
F 0 "R13" V 8480 4600 50  0000 C CIN
F 1 "10K" V 8400 4600 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 8330 4600 50  0001 C CNN
F 3 "" H 8400 4600 50  0000 C CNN
F 4 "RMCF0805JT10K0" H 8400 4600 60  0001 C CNN "manf#"
	1    8400 4600
	1    0    0    -1  
$EndComp
$Comp
L R R22
U 1 1 58999FEA
P 8650 4800
F 0 "R22" V 8730 4800 50  0000 C CIN
F 1 "10K" V 8650 4800 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 8580 4800 50  0001 C CNN
F 3 "" H 8650 4800 50  0000 C CNN
F 4 "RMCF0805JT10K0" H 8650 4800 60  0001 C CNN "manf#"
	1    8650 4800
	0    1    1    0   
$EndComp
$Comp
L R R15
U 1 1 589A56D1
P 9400 4600
F 0 "R15" V 9480 4600 50  0000 C CIN
F 1 "10K" V 9400 4600 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 9330 4600 50  0001 C CNN
F 3 "" H 9400 4600 50  0000 C CNN
F 4 "RMCF0805JT10K0" H 9400 4600 60  0001 C CNN "manf#"
	1    9400 4600
	1    0    0    -1  
$EndComp
$Comp
L R R23
U 1 1 589A5BF6
P 9650 4800
F 0 "R23" V 9730 4800 50  0000 C CIN
F 1 "10K" V 9650 4800 50  0000 C CNN
F 2 "Resistors_SMD:R_0805_HandSoldering" V 9580 4800 50  0001 C CNN
F 3 "" H 9650 4800 50  0000 C CNN
F 4 "RMCF0805JT10K0" H 9650 4800 60  0001 C CNN "manf#"
	1    9650 4800
	0    1    1    0   
$EndComp
$Comp
L C C4
U 1 1 589A9856
P 4750 4650
F 0 "C4" H 4775 4750 50  0000 L CNN
F 1 ".1u" H 4775 4550 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 4788 4500 50  0001 C CNN
F 3 "" H 4750 4650 50  0000 C CNN
F 4 "08055C104KAT2A" H 4750 4650 60  0001 C CNN "manf#"
	1    4750 4650
	1    0    0    -1  
$EndComp
$Comp
L C C5
U 1 1 589A9947
P 4450 5700
F 0 "C5" H 4475 5800 50  0000 L CNN
F 1 ".1u" H 4475 5600 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 4488 5550 50  0001 C CNN
F 3 "" H 4450 5700 50  0000 C CNN
F 4 "08055C104KAT2A" H 4450 5700 60  0001 C CNN "manf#"
	1    4450 5700
	1    0    0    -1  
$EndComp
$Comp
L C C6
U 1 1 589A9A45
P 5800 5350
F 0 "C6" H 5825 5450 50  0000 L CNN
F 1 ".1u" H 5825 5250 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 5838 5200 50  0001 C CNN
F 3 "" H 5800 5350 50  0000 C CNN
F 4 "08055C104KAT2A" H 5800 5350 60  0001 C CNN "manf#"
	1    5800 5350
	1    0    0    -1  
$EndComp
$Comp
L C C7
U 1 1 589A9B2A
P 6900 5350
F 0 "C7" H 6925 5450 50  0000 L CNN
F 1 ".1u" H 6925 5250 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 6938 5200 50  0001 C CNN
F 3 "" H 6900 5350 50  0000 C CNN
F 4 "08055C104KAT2A" H 6900 5350 60  0001 C CNN "manf#"
	1    6900 5350
	1    0    0    -1  
$EndComp
$Comp
L C C8
U 1 1 589A9C14
P 7850 5350
F 0 "C8" H 7875 5450 50  0000 L CNN
F 1 ".1u" H 7875 5250 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 7888 5200 50  0001 C CNN
F 3 "" H 7850 5350 50  0000 C CNN
F 4 "08055C104KAT2A" H 7850 5350 60  0001 C CNN "manf#"
	1    7850 5350
	1    0    0    -1  
$EndComp
$Comp
L C C9
U 1 1 589A9D01
P 8850 5350
F 0 "C9" H 8875 5450 50  0000 L CNN
F 1 ".1u" H 8875 5250 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 8888 5200 50  0001 C CNN
F 3 "" H 8850 5350 50  0000 C CNN
F 4 "08055C104KAT2A" H 8850 5350 60  0001 C CNN "manf#"
	1    8850 5350
	1    0    0    -1  
$EndComp
$Comp
L C C10
U 1 1 589A9DFD
P 9850 5350
F 0 "C10" H 9875 5450 50  0000 L CNN
F 1 ".1u" H 9875 5250 50  0000 L CNN
F 2 "Capacitors_SMD:C_0805_HandSoldering" H 9888 5200 50  0001 C CNN
F 3 "" H 9850 5350 50  0000 C CNN
F 4 "08055C104KAT2A" H 9850 5350 60  0001 C CNN "manf#"
	1    9850 5350
	1    0    0    -1  
$EndComp
$Comp
L SW_PUSH SW3
U 1 1 589B3124
P 4100 5700
F 0 "SW3" H 4250 5810 50  0000 C CNN
F 1 "SW_PUSH" H 4100 5620 50  0000 C CNN
F 2 "kDrive:Cherry_MX1A-C1NW" H 4100 5700 50  0001 C CNN
F 3 "" H 4100 5700 50  0000 C CNN
F 4 "MX1A-C1NW" H 4100 5700 60  0001 C CNN "manf#"
F 5 "540-MX1A-C1NW" H 4100 5700 60  0001 C CNN "mouser#"
F 6 "CH310-ND" H 4100 5700 60  0001 C CNN "digikey#"
	1    4100 5700
	0    -1   -1   0   
$EndComp
$Comp
L SW_PUSH SW2
U 1 1 589B3A6F
P 4300 4500
F 0 "SW2" H 4450 4610 50  0000 C CNN
F 1 "SW_PUSH" H 4300 4420 50  0000 C CNN
F 2 "kDrive:Cherry_MX1A-C1NW" H 4300 4500 50  0001 C CNN
F 3 "" H 4300 4500 50  0000 C CNN
F 4 "MX1A-C1NW" H 4300 4500 60  0001 C CNN "manf#"
F 5 "540-MX1A-C1NW" H 4300 4500 60  0001 C CNN "mouser#"
F 6 "CH310-ND" H 4300 4500 60  0001 C CNN "digikey#"
	1    4300 4500
	0    -1   -1   0   
$EndComp
$Comp
L SW_PUSH SW4
U 1 1 589B3D68
P 5350 5350
F 0 "SW4" H 5500 5460 50  0000 C CNN
F 1 "SW_PUSH" H 5350 5270 50  0000 C CNN
F 2 "kDrive:Cherry_MX1A-C1NW" H 5350 5350 50  0001 C CNN
F 3 "" H 5350 5350 50  0000 C CNN
F 4 "MX1A-C1NW" H 5350 5350 60  0001 C CNN "manf#"
F 5 "540-MX1A-C1NW" H 5350 5350 60  0001 C CNN "mouser#"
F 6 "CH310-ND" H 5350 5350 60  0001 C CNN "digikey#"
	1    5350 5350
	0    -1   -1   0   
$EndComp
$Comp
L SW_PUSH SW5
U 1 1 589B4855
P 6400 5350
F 0 "SW5" H 6550 5460 50  0000 C CNN
F 1 "SW_PUSH" H 6400 5270 50  0000 C CNN
F 2 "kDrive:Cherry_MX1A-C1NW" H 6400 5350 50  0001 C CNN
F 3 "" H 6400 5350 50  0000 C CNN
F 4 "MX1A-C1NW" H 6400 5350 60  0001 C CNN "manf#"
F 5 "540-MX1A-C1NW" H 6400 5350 60  0001 C CNN "mouser#"
F 6 "CH310-ND" H 6400 5350 60  0001 C CNN "digikey#"
	1    6400 5350
	0    -1   -1   0   
$EndComp
$Comp
L SW_PUSH SW6
U 1 1 589B4AC0
P 7400 5350
F 0 "SW6" H 7550 5460 50  0000 C CNN
F 1 "SW_PUSH" H 7400 5270 50  0000 C CNN
F 2 "kDrive:Cherry_MX1A-C1NW" H 7400 5350 50  0001 C CNN
F 3 "" H 7400 5350 50  0000 C CNN
F 4 "MX1A-C1NW" H 7400 5350 60  0001 C CNN "manf#"
F 5 "540-MX1A-C1NW" H 7400 5350 60  0001 C CNN "mouser#"
F 6 "CH310-ND" H 7400 5350 60  0001 C CNN "digikey#"
	1    7400 5350
	0    -1   -1   0   
$EndComp
$Comp
L SW_PUSH SW7
U 1 1 589B5477
P 8400 5350
F 0 "SW7" H 8550 5460 50  0000 C CNN
F 1 "SW_PUSH" H 8400 5270 50  0000 C CNN
F 2 "kDrive:Cherry_MX1A-C1NW" H 8400 5350 50  0001 C CNN
F 3 "" H 8400 5350 50  0000 C CNN
F 4 "MX1A-C1NW" H 8400 5350 60  0001 C CNN "manf#"
F 5 "540-MX1A-C1NW" H 8400 5350 60  0001 C CNN "mouser#"
F 6 "CH310-ND" H 8400 5350 60  0001 C CNN "digikey#"
	1    8400 5350
	0    -1   -1   0   
$EndComp
$Comp
L SW_PUSH SW8
U 1 1 589B574C
P 9400 5350
F 0 "SW8" H 9550 5460 50  0000 C CNN
F 1 "SW_PUSH" H 9400 5270 50  0000 C CNN
F 2 "kDrive:Cherry_MX1A-C1NW" H 9400 5350 50  0001 C CNN
F 3 "" H 9400 5350 50  0000 C CNN
F 4 "MX1A-C1NW" H 9400 5350 60  0001 C CNN "manf#"
F 5 "540-MX1A-C1NW" H 9400 5350 60  0001 C CNN "mouser#"
F 6 "CH310-ND" H 9400 5350 60  0001 C CNN "digikey#"
	1    9400 5350
	0    -1   -1   0   
$EndComp
$Comp
L CL-SB-13B-01T U5
U 1 1 58996E69
P 1650 2100
F 0 "U5" H 1350 2000 60  0000 C CNN
F 1 "CL-SB-13B-01T" H 1650 2200 60  0000 C CNN
F 2 "kDrive:CL-SB-13B-01T" H 1650 2100 60  0001 C CNN
F 3 "" H 1650 2100 60  0001 C CNN
F 4 "CL-SB-13B-01T" H 1650 2100 60  0001 C CNN "manf#"
	1    1650 2100
	1    0    0    -1  
$EndComp
Wire Wire Line
	8400 3700 8400 4450
Wire Wire Line
	3100 3700 9400 3700
Wire Wire Line
	3100 3700 3100 4400
Wire Wire Line
	4300 3800 4300 3700
Connection ~ 4300 3700
Wire Wire Line
	6400 4450 6400 3700
Connection ~ 6400 3700
Wire Wire Line
	7400 4450 7400 3700
Connection ~ 7400 3700
Wire Wire Line
	4100 4900 4100 4800
Wire Wire Line
	4100 4800 3750 4800
Wire Wire Line
	3750 4800 3750 3700
Connection ~ 3750 3700
Wire Wire Line
	8150 1800 7650 1800
Wire Wire Line
	7650 1800 7650 3100
Wire Wire Line
	9450 3100 6700 3100
Wire Wire Line
	9450 1800 9450 3100
Wire Wire Line
	9450 1800 8950 1800
Wire Wire Line
	8950 2200 9150 2200
Wire Wire Line
	8950 2150 9150 2150
Wire Wire Line
	8950 2100 9150 2100
Wire Wire Line
	8950 2050 9150 2050
Wire Wire Line
	8950 2000 9150 2000
Wire Wire Line
	8950 1950 9150 1950
Wire Wire Line
	9400 3700 9400 4450
Connection ~ 8400 3700
Connection ~ 9400 3700
Wire Wire Line
	8950 2250 9150 2250
Wire Wire Line
	8950 2300 9150 2300
Wire Wire Line
	8150 1850 7950 1850
Wire Wire Line
	8150 1900 7950 1900
Wire Wire Line
	3550 750  3300 750 
Wire Wire Line
	3550 850  3300 850 
Wire Wire Line
	3550 950  3300 950 
Wire Wire Line
	3550 1050 3300 1050
Wire Wire Line
	3550 1150 3300 1150
Wire Wire Line
	3550 1250 3300 1250
Wire Wire Line
	3550 1350 3300 1350
Wire Wire Line
	3550 1450 3300 1450
Wire Wire Line
	3550 1550 3300 1550
Wire Wire Line
	3550 1650 3300 1650
Wire Wire Line
	4050 1650 4300 1650
Wire Wire Line
	4050 1550 4300 1550
Wire Wire Line
	4050 1450 4300 1450
Wire Wire Line
	4050 1350 4300 1350
Wire Wire Line
	4050 1250 4300 1250
Wire Wire Line
	4050 1150 4300 1150
Wire Wire Line
	4050 1050 4300 1050
Wire Wire Line
	4050 950  4300 950 
Wire Wire Line
	4050 850  4300 850 
Wire Wire Line
	4050 750  4300 750 
Wire Wire Line
	5450 1650 5700 1650
Wire Wire Line
	5450 1550 5700 1550
Wire Wire Line
	5450 1450 5700 1450
Wire Wire Line
	5450 1350 5700 1350
Wire Wire Line
	5450 1250 5700 1250
Wire Wire Line
	5450 1150 5700 1150
Wire Wire Line
	5450 1050 5700 1050
Wire Wire Line
	5450 950  5700 950 
Wire Wire Line
	5450 850  5700 850 
Wire Wire Line
	5450 750  5700 750 
Wire Wire Line
	4700 1450 4950 1450
Wire Wire Line
	4700 1350 4950 1350
Wire Wire Line
	4700 1250 4950 1250
Wire Wire Line
	4700 1150 4950 1150
Wire Wire Line
	4700 1050 4950 1050
Wire Wire Line
	4700 950  4950 950 
Wire Wire Line
	4700 850  4950 850 
Wire Wire Line
	4700 750  4950 750 
Wire Wire Line
	800  2250 800  2400
Wire Wire Line
	800  1950 800  1650
Wire Wire Line
	800  1650 1350 1650
Wire Wire Line
	1350 1650 1350 1900
Wire Wire Line
	1550 2300 1550 2550
Wire Wire Line
	1950 1650 1950 1900
Wire Wire Line
	8950 1850 9150 1850
Wire Wire Line
	8950 1900 9150 1900
Wire Wire Line
	8950 2350 9450 2350
Connection ~ 9450 2350
Wire Wire Line
	8150 2350 7650 2350
Connection ~ 7650 2350
Wire Wire Line
	1750 6300 1500 6300
Wire Wire Line
	1750 6200 1500 6200
Wire Wire Line
	1400 5900 1750 5900
Wire Wire Line
	1400 5800 1750 5800
Wire Wire Line
	2700 5400 2450 5400
Wire Wire Line
	1750 5400 1500 5400
Wire Wire Line
	1750 5500 1500 5500
Wire Wire Line
	1750 6500 1500 6500
Wire Wire Line
	2450 6400 2700 6400
Wire Wire Line
	1750 6400 1500 6400
Wire Wire Line
	1500 4900 1500 5100
Wire Wire Line
	1800 4900 1800 5100
Wire Wire Line
	2100 4900 2100 5100
Wire Wire Line
	1200 4900 1200 5100
Wire Wire Line
	900  4900 900  5100
Wire Wire Line
	2450 5500 2700 5500
Wire Wire Line
	1400 5700 1400 5800
Wire Wire Line
	800  5700 1400 5700
Connection ~ 1050 5700
Wire Wire Line
	800  6000 1400 6000
Wire Wire Line
	1400 6000 1400 5900
Connection ~ 1050 6000
Wire Wire Line
	1500 6800 1500 7000
Wire Wire Line
	2700 6400 2700 6500
Wire Wire Line
	2700 6800 2700 7000
Wire Wire Line
	6450 1600 6150 1600
Wire Wire Line
	6950 1400 7250 1400
Wire Wire Line
	6950 1300 7250 1300
Wire Wire Line
	6450 1400 6150 1400
Wire Wire Line
	6450 1700 6150 1700
Wire Wire Line
	1200 6200 900  6200
Wire Wire Line
	8650 2650 8650 2850
Wire Wire Line
	8700 2650 8700 2850
Wire Wire Line
	9150 1850 9150 1650
Wire Wire Line
	9150 1350 9150 1150
Wire Wire Line
	4700 1650 4950 1650
Wire Wire Line
	4700 1550 4950 1550
Wire Wire Line
	7400 2700 7400 2600
Connection ~ 7650 3100
Wire Wire Line
	7050 2600 7050 2700
Connection ~ 7400 3100
Wire Wire Line
	7050 2150 7050 2300
Connection ~ 7050 3100
Wire Wire Line
	6700 2700 6700 2600
Wire Wire Line
	6700 2300 6700 2150
Wire Wire Line
	8150 1950 7950 1950
Wire Wire Line
	8150 2000 7950 2000
Wire Wire Line
	8150 2150 7950 2150
Wire Wire Line
	8150 2250 7950 2250
Wire Wire Line
	8150 2050 7950 2050
Wire Wire Line
	8150 2100 7950 2100
Wire Wire Line
	8150 2200 7950 2200
Wire Wire Line
	8150 2300 7950 2300
Wire Wire Line
	8450 2650 8450 2850
Wire Wire Line
	8500 2650 8500 2850
Wire Wire Line
	8550 2650 8550 2850
Wire Wire Line
	7400 2150 7400 2300
Wire Wire Line
	10150 1600 10750 1600
Connection ~ 10450 1600
Wire Wire Line
	10150 1600 10150 1700
Wire Wire Line
	10450 1600 10450 1700
Wire Wire Line
	10750 1600 10750 1700
Wire Wire Line
	10150 2000 10150 2100
Wire Wire Line
	10150 2100 10750 2100
Wire Wire Line
	10750 2100 10750 2000
Wire Wire Line
	10450 2000 10450 2100
Connection ~ 10450 2100
Wire Wire Line
	2450 5600 2700 5600
Wire Wire Line
	8600 2650 8600 2850
Wire Wire Line
	1500 3900 1500 4150
Wire Wire Line
	3100 6000 3100 5650
Wire Wire Line
	3500 5200 3500 4800
Wire Wire Line
	3500 4800 3450 4800
Wire Wire Line
	3150 4800 3100 4800
Wire Wire Line
	3100 4700 3100 5050
Connection ~ 3100 4800
Wire Wire Line
	3500 5500 3500 6000
Connection ~ 3500 6000
Wire Wire Line
	4100 5200 4100 5400
Wire Wire Line
	4100 5300 4150 5300
Connection ~ 4100 5300
Wire Wire Line
	4450 5300 4450 5550
Wire Wire Line
	4450 6000 4450 5850
Connection ~ 4450 6000
Wire Wire Line
	4300 4100 4300 4200
Wire Wire Line
	4400 4150 4300 4150
Connection ~ 4300 4150
Wire Wire Line
	4300 4800 4750 4800
Wire Wire Line
	4750 4800 4750 6000
Connection ~ 4750 6000
Wire Wire Line
	4750 4500 4750 4150
Wire Wire Line
	4750 4150 4700 4150
Wire Wire Line
	5350 4400 5350 3700
Connection ~ 5350 3700
Wire Wire Line
	5350 4700 5350 5050
Wire Wire Line
	5350 6000 5350 5650
Connection ~ 5350 6000
Wire Wire Line
	5450 4800 5350 4800
Connection ~ 5350 4800
Wire Wire Line
	5750 4800 5800 4800
Wire Wire Line
	5800 4800 5800 5200
Wire Wire Line
	5800 6000 5800 5500
Connection ~ 5800 6000
Wire Wire Line
	6400 4750 6400 5050
Wire Wire Line
	6400 6000 6400 5650
Connection ~ 6400 6000
Wire Wire Line
	6400 4800 6500 4800
Connection ~ 6400 4800
Wire Wire Line
	6800 4800 6900 4800
Wire Wire Line
	6900 4800 6900 5200
Wire Wire Line
	6900 6000 6900 5500
Connection ~ 6900 6000
Wire Wire Line
	7400 4750 7400 5050
Wire Wire Line
	7400 6000 7400 5650
Connection ~ 7400 6000
Wire Wire Line
	7500 4800 7400 4800
Connection ~ 7400 4800
Wire Wire Line
	7800 4800 7850 4800
Wire Wire Line
	7850 4800 7850 5200
Wire Wire Line
	7850 6000 7850 5500
Connection ~ 7850 6000
Wire Wire Line
	8400 4750 8400 5050
Wire Wire Line
	8400 6000 8400 5650
Connection ~ 8400 6000
Wire Wire Line
	8500 4800 8400 4800
Connection ~ 8400 4800
Wire Wire Line
	8800 4800 8850 4800
Wire Wire Line
	8850 4800 8850 5200
Wire Wire Line
	8850 6000 8850 5500
Connection ~ 8850 6000
Wire Wire Line
	9400 4750 9400 5050
Wire Wire Line
	9400 4800 9500 4800
Connection ~ 9400 4800
Wire Wire Line
	9800 4800 9850 4800
Wire Wire Line
	9850 4800 9850 5200
Wire Wire Line
	9400 6000 9400 5650
Connection ~ 9400 6000
Wire Wire Line
	9850 6000 9850 5500
Connection ~ 9850 6000
Wire Wire Line
	3100 6000 10000 6000
Wire Wire Line
	1750 2300 1750 2400
Connection ~ 1750 2400
Wire Wire Line
	1750 2400 2050 2400
Connection ~ 4100 6000
$EndSCHEMATC
