OpenRocket-ActEC
==========

Overview
--------

OpenRocket-ActEC (Active Energy Control) is a testbed for high-powered rocket flight computers that control active energy elements during the boost/coast phase of a rocket. It is a derivative work of the OpenRocket software project and Bill Kuker's Dispersion project.

ActEC is intended foremost to torture test candidate control algorithms. To implement this functionality, ActEC exploits the medium-fidelity flight simulation capabilities of OpenRocket to provide a realistic environment against which a candidate controller can be tested.

ActEC emulates onboard electronic sensors that can provide input to a controller. Their dynamics can be 'fuzzed' such that their outputs are distorted to emulate real-world noise and other imperfections. The flight conditions (including air temperate, density, etc.) as well as the physical properties/functionalities of the rocket and its control system can be 'fuzzed' to model inherent imperfections in real-world measurements.

As such, ActEC provides immediate, realistic controller performance feedback that can augment/replace real test flight data.

License
-------

OpenRocket is an Open Source project licensed under the [GNU GPL](http://openrocket.sourceforge.net/license.html). This means that the software is free to use for whatever purposes, and the source code is also available for studying and extending. ActEC is such an extension.

Contributing
------------

ActEC is developed by Chris Andre from RPI's NASA USLI team.