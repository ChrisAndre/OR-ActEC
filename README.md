OpenRocket-ActEC
==========

Website: http://www.chrisandre.org/OR-ActEC/

This project is not in development at this time, as it no longer has a developer. Chris will be taking leave during the Spring 2016 semester to work full-time on co-op. See https://github.com/ChrisAndre/qaec

Overview
--------

OpenRocket-ActEC (Active Energy Control) is a testbed for high-power rocket flight computers that control active energy elements during the boost/coast phase of a rocket.

ActEC is intended foremost to perform sensitivity analyses on control algorithms. ActEC makes use of the pre-existing OpenRocket project and Bill Kuker's Dispersion project to provide an accurate model of a rocket's flight.

ActEC is being developed in response to RPI's participation in the 2016 NASA Student Launch competition. RPI's competition objective is to guide a rocket as close to 5280 feet as possible using variable drag elements; ActEC will fulfill the need for validation of our control systems.

Current features/wishlist:
-------------------------

**Integrated 3D graphing of trajectories (Complete)**

-- 3D viewport integrated into simulation window. (Adapted from Bill Kuker's Dispersion project)

**Batch simulations (Partially Complete)**

-- Can now run and view large groups of simulations in the torture test window.

**Flight computer interface (Partially Complete)**

-- Can now implement a rudimentary flight computer in Java.

-- Planning on exposing the interface to Jython code so that it doesn't have to be recompiled every time!

**Control system interface**

-- Should have a few code panels to directly implement controllers.

**Virtual sensors**

-- Altitude, zenith angle sensors implemented.

-- Will expose to Jython.

**Control element flight models (Complete for now - 2016 USLI generalized drag flaps are in!)**

-- Some time was spent flipping the internals of openrocket's simulation engine, since it did not allow for generating 3-axis rotational forces in a trivial way (at least to my eye). The internals have been modified to allow for any moment in the rocket reference frame easily.

**Sensor fuzzing**

-- Planning on fuzzing sensor outputs to force us to account for noise. Previous teams had issues with this.

**Launch condition fuzzing**

-- The core of the sensitivity analysis package. Will account for motor thrust anomalies, wind speed, launch rod angle, rocket mass, drag anomalies, etc.

About RPI's Rocket and ActEC
----------------------------

RPI's high-power rocketry team (RRS) has chosen to integrate controllable drag flaps resembling spoilers along the body of the competition rocket. These actuated flaps should be capable of 1-dimensional drag control, which will guide the rocket to as close to 5280 feet as possible. Chris Andre (currently the only developer) is building ActEC so that the team can test out candidate control algorithms. Since the team only has one full-scale test planned before the competition launch, it is critical that we have some verifiable model of our objective performance.

Previous teams have rolled custom code to test controls, but the required operator knowledge is high. In our case, this code has either been lost or never existed! To rectify this, we intend to develop an easy-to-operate test 'suite' for developing control systems and testing them directly. This will be a great investment in not only our 2016 competition performance, but in later competitions, since it will be easy to pick up. The project is public, so other teams may make use of this, even those in direct competition with us. To this end, ActEC is based on the OpenRocket project, which is a GUI-based application for constructing and simulating model rockets. As such, ActEC will cover ground-up rocket design and control system testing in one easy-to-use application.

License
-------

OpenRocket is an Open Source project licensed under the [GNU GPL](http://openrocket.sourceforge.net/license.html). ActEC bears the same license.

Contributing
------------

ActEC is primarily developed by Chris Andre from RPI's NASA USLI team.
