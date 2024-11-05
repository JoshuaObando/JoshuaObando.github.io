document.addEventListener('DOMContentLoaded', function() {
    const timeSelect = document.getElementById('time');
    const dateInput = document.getElementById('date');
    const serviceSelect = document.getElementById('service');
    const barberSelect = document.getElementById('barber');

    // Establecer fecha mínima como hoy
    const today = new Date().toISOString().split('T')[0];
    dateInput.min = today;

    // Generar horas disponibles
    function generateTimeSlots() {
        timeSelect.innerHTML = '<option value="">Seleccione una hora</option>';
        
        const selectedDate = new Date(dateInput.value);
        const day = selectedDate.getDay();

        // No generar horas si es domingo (0)
        if (day === 0) {
            alert('Lo sentimos, no abrimos los domingos');
            dateInput.value = '';
            return;
        }

        const startHour = day === 6 ? 10 : 9; // Sábado comienza a las 10, otros días a las 9
        const endHour = day === 6 ? 18 : 20;  // Sábado termina a las 18, otros días a las 20

        for (let hour = startHour; hour < endHour; hour++) {
            for (let minute of ['00', '30']) {
                const timeValue = `${hour.toString().padStart(2, '0')}:${minute}`;
                const option = document.createElement('option');
                option.value = timeValue;
                option.textContent = timeValue;
                timeSelect.appendChild(option);
            }
        }
    }

    dateInput.addEventListener('change', generateTimeSlots);

    // Pre-seleccionar servicio si se hace clic en un botón de reserva
    document.querySelectorAll('.btn-service').forEach(button => {
        button.addEventListener('click', function(e) {
            const serviceName = this.closest('.service-card').querySelector('h3').textContent;
            const serviceSelect = document.getElementById('service');
            
            for(let option of serviceSelect.options) {
                if(option.text.startsWith(serviceName)) {
                    serviceSelect.value = option.value;
                    break;
                }
            }
        });
    });

    // Validación del formulario
    window.validateForm = function() {
        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;
        const phone = document.getElementById('phone').value;
        const service = serviceSelect.value;
        const barber = barberSelect.value;
        const date = dateInput.value;
        const time = timeSelect.value;

        if (!name || !email || !phone || !service || !barber || !date || !time) {
            alert('Por favor, complete todos los campos requeridos.');
            return false;
        }

        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            alert('Por favor, introduzca un correo electrónico válido.');
            return false;
        }

        // Validación para números de teléfono de Costa Rica (8 dígitos)
        const phoneRegex = /^[2-8]\d{3}-?\d{4}$/;
        if (!phoneRegex.test(phone)) {
            alert('Por favor, introduzca un número de teléfono válido de Costa Rica (8 dígitos).');
            return false;
        }

        // Aquí se podría agregar la lógica para enviar los datos a un servidor
        alert('¡Reserva realizada con éxito! Nos pondremos en contacto contigo pronto.');
        return false; // Previene el envío del formulario por ahora
    };
});