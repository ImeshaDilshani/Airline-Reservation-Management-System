document.getElementById('flightForm').addEventListener('submit', function(event) {
  event.preventDefault(); // Prevent the form from submitting normally

  const selectedFlight = document.querySelector('input[name="flight"]:checked');
  if (selectedFlight) {
      const flightName = selectedFlight.nextElementSibling.querySelector('.flight-name').textContent;
      const flightRoute = selectedFlight.nextElementSibling.querySelector('.flight-route').textContent;
      const flightTime = selectedFlight.nextElementSibling.querySelector('.flight-time').textContent;

      const redirectUrl = `ticket.html?name=${encodeURIComponent(flightName)}&route=${encodeURIComponent(flightRoute)}&time=${encodeURIComponent(flightTime)}`;
      window.location.href = redirectUrl;
  } else {
      alert('Please select a flight.');
  }
});