const urlParams = new URLSearchParams(window.location.search);
        const flightName = urlParams.get('name');
        const flightRoute = urlParams.get('route');
        const flightTime = urlParams.get('time');

        if (flightName && flightRoute && flightTime) {
            document.getElementById('displayFlightName').textContent = flightName;
            document.getElementById('displayFlightRoute').textContent = flightRoute;
            document.getElementById('displayFlightTime').textContent = flightTime;
        } else {
            document.write('<p>No flight information available.</p>');
        }