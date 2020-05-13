#################################################################################################################
###########                          Editing Simulation Parameters                               ################
#################################################################################################################

simulationParameters can be edited in the Config.json file:

t:                    the starting time
dt:                   the timestep between each calculation within the simulation
maxt:                 the finishing time
outputRes:            print every nth result, for example if outputRes 500, print results every 500 iterations.
rungeKuttaClassName:  which mathematical model to use for the simulation



#################################################################################################################
##########                          How To Modify Transmission Rates                               ##############
#################################################################################################################

When specifiying the population parameters, the s, i & r values are the initial values for the susceptable/infected/recovered respectively.

the tranmission rates should be handled as follows:
Example Transmission Rate JSon Object:

  "transmissionRates": [
    [				This is group 0
      1.0,		Transmission rate 0 --> 0 (Internal)
      2.0,		Transmission rate 0 --> 1 (External)
      3.0 		Transmission rate 0 --> 2 (External)
    ],
    [				This is group 1
      1.5,		Transmission rate 1 --> 0 (External)
      1.2,		Transmission rate 1 --> 1 (Internal)
      1.2		Transmission rate 1 --> 2 (External)
    ]
    [				This is group 2
      1.5,		Transmission rate 2 --> 0 (External)
      1.2,		Transmission rate 2 --> 1 (External)
      1.2		Transmission rate 2 --> 2 (Internal)
    ]
  ]

This can be scaled up for any number of groups. It amounts to a transmission matrix of [n][n].
A value (double) must be entered for every element of the matrix.



#################################################################################################################
##########                                 Using The Curl Interface                              ################
#################################################################################################################

Example Curl POST Command:
curl -X POST address:port/resultstream --data-binary @<PathToConfig> --header "Content-Type: application/json" pause
curl -X POST localhost:8080/resultstream --data-binary @Config.json --header "Content-Type: application/json" pause


Data is returned as an HttpServletResponse OutputStream,
if you run this command from the commandline it should just print to the commandline.