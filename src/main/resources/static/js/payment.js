// Sample data for demonstration
const bookingData = {
    airplaneType: "Boeing 737",
    departureTime: "2023-08-15 14:00",
    startPlace: "New York",
    destination: "Los Angeles",
    ticketCount: 3,
    ticketNumbers: ["A1234", "A1235", "A1236"],
    ticketPrice: 150.00,
  };

  // Function to update the displayed booking details
  function updateBookingDetails() {
    document.getElementById("airplane-type").textContent = bookingData.airplaneType;
    document.getElementById("departure-time").textContent = bookingData.departureTime;
    document.getElementById("start-place").textContent = bookingData.startPlace;
    document.getElementById("destination").textContent = bookingData.destination;
    document.getElementById("ticket-count").textContent = bookingData.ticketCount;
    document.getElementById("ticket-numbers").textContent = bookingData.ticketNumbers.join(", ");
    document.getElementById("ticket-price").textContent = `$${bookingData.ticketPrice.toFixed(2)}`;
  }

  // Event listener for form submission
  document.getElementById("payment-form").addEventListener("submit", function (event) {
    event.preventDefault();
    const cardHolder = document.getElementById("card-holder").value;
    const cardNumber = document.getElementById("card-number").value;
    const expiryDate = document.getElementById("expiry-date").value;
    const cvv = document.getElementById("cvv").value;

    // Here, you can add the code to process the payment information, e.g., sending to a backend for processing.

    // For this example, we'll just show an alert indicating payment success.
    alert("Payment Successful!");

    // Clear the form fields after successful payment
    document.getElementById("card-holder").value = "";
    document.getElementById("card-number").value = "";
    document.getElementById("expiry-date").value = "";
    document.getElementById("cvv").value = "";
  });

  // Update the booking details on page load
  updateBookingDetails();
