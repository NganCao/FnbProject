Flow code
├  │  └  ─

Run testNG.xml by mvn: mvn clean test -Dsuite-xml=<testng.xml file path>
Download dependency by mvn: mvn dependency:resolve
src
├──main
│     └──java
│            └──com
│    			│
│    		    ├──FnB // Project Name
│    			│
│    		    └──Utils 
│    			    │
│    		        ├──//Helpers //Shared common func, Read data files, listener, capture, reader json files
│    		        │
│    		        └──platform //Locale //language.json files
│
│   		    
├──test
│    │
│    └──java
│    	 │
│	 └──com
│		└──FnB //Project Name
│		    │
│		    ├──drivermanager //drivers manager 
│		    │
│		    ├──Web
│		    │	│
│		    │	├──SetUp //core classes that form the framework
│		    │	│    │
│		    │	│    ├──BaseSetUp // init, setup driver, env base 
│		    │	│    │
│		    │	│    └──BaseTest // setup Scenario Root
│		    │	│    
│		    │	├──Admin //components and locators of each webpage
│		    │	│    │
│		    │	│    ├──Pages
│		    │	│    │ 	  │
│		    │	│    │ 	  ├─Page setup //navigate to test methods class
│		    │	│    │ 	  │
│		    │	│    │ 	  └──Login
│		    │	│    │		│
│		    │	│    │		├── LoginPage //test methods	
│		    │	│    │  	│
│		    │	│    │ 		└── Data test // prepare data
│		    │	│    └──Scenario_test 	    
│		    │	│    		│
│ 	    	│   │		    └── Login
│		    │	│			      │
│		    │	│			      └─ Login test //Write scenario test scripts
│		    │	├──Pos web	
│		    │	│    │
│		    │	│    ├──Pages
│		    │	│    │ 	  │
│		    │	│    │ 	  ├─Page setup //navigate to test methods class
│		    │	│    │ 	  │
│		    │	│    │ 	  └──Login
│		    │	│    │		│
│		    │	│    │		├── LoginPage //test methods	
│		    │	│    │  	│
│		    │	│    │ 		└── Data test // prepare data
│		    │	│    └──Scenario_test 	    
│		    │	│    		│
│ 	    	│   │		    └── Login
│		    │	│			      │
│		    │	│			      └ Login test //Write scenario test scripts
│		    │	│    
│		    │	└──Store web
│		    │	     │
│		    │	     ├─Theme 1
│		    │	     │	  │
│		    │	     │    ├──Pages
│		    │	     │ 	  │	│
│		    │	     │ 	  │	├─Page setup //navigate to test methods class
│		    │	     │ 	  │	│
│		    │	     │ 	  │	└──Login
│		    │	     │	  │		│
│		    │	     │	  │		├── LoginPage //test methods	
│		    │	     │    │		│
│		    │	     │ 	  │		└── Data test // prepare data
│		    │	     │	  └──Scenario_test 	    
│		    │	     │		    │
│ 	    	│        │		    └── Login
│		    │	     │			      │
│		    │	     │			      └ Login test //Write scenario test scripts
│		    │	     │
│		    │	     └──Theme 2
│		    │	     	  │
│		    │	          ├──Pages
│		    │	      	  │	│
│		    │	      	  │	├─Page setup //navigate to test methods class
│		    │	      	  │	│
│		    │	      	  │	└──Login
│		    │	     	  │		│
│		    │	     	  │		├── LoginPage //test methods	
│		    │	          │		│
│		    │	      	  │		└── Data test // prepare data
│		    │	     	  └──Scenario_test 	    
│		    │	     		│
│ 	    	│   		    └── Login
│		    │				        │
│		    │				        └ Login test //Write scenario test scripts
│           └──App
│		    	│
│		    	│
│		    	├──SetUp //core classes that form the framework
│		    	│    │
│		    	│    ├──BaseSetUp // init, setup driver, env base 
│		    	│    │
│		    	│    └──BaseTest // setup Scenario Root
│		    	├──Android
│		    	│     │
│		    	│     │
│			    │     ├──KemStore
│		        │     │	  │
│		     	│     │   ├──Pages
│		    	│     │	  │	│
│		    	│     │   │	├─Page setup //navigate to test methods class
│		    	│     │   │	│
│		    	│     │   │	└──Login
│		    	│     │	  │	     │
│		    	│     │	  │	     ├── LoginPage //test methods	
│		    	│     │   │	     │
│		    	│     │   │	     └── Data test // prepare data
│		    	│     │	  └──Scenario_test 	    
│		    	│     │		    │
│ 	    	    │     │		    └── Login
│		    	│     │			        │
│		    	│     │			        └ Login test //Write scenario test scripts
│			    │     │
│			    │     └	LinStore
│		    	│     	  │
│		    	│         ├──Pages
│		    	│      	  │	│
│		    	│      	  │	├─Page setup //navigate to test methods class
│		    	│      	  │	│
│		    	│      	  │	└──Login
│		    	│     	  │		│
│		    	│     	  │		├── LoginPage //test methods	
│		    	│         │		│
│		    	│      	  │		└── Data test // prepare data
│		    	│     	  └──Scenario_test 	    
│		    	│     		│
│ 	    	    │		    └── Login
│		    	│			        │
│		    	│			        └ Login test //Write scenario test scripts
│			    │
│			    └───ios
│
│
│
├──Target
│
├──pom.xml
│
├──testNG.xml
│
├──Test-Output
│
├──resource
│	│
│	└── config.json //Config used for the system
│
└──Screenshot

