document.addEventListener('DOMContentLoaded', function () {

    // Ativa tooltips do Bootstrap, se houver algum elemento marcado
    var tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]');
    tooltipTriggerList.forEach(function (el) {
        new bootstrap.Tooltip(el);
    });

    // Confirmação simples antes de devolver um exemplar
    document.querySelectorAll('a[href*="/devolver"]').forEach(function (link) {
        link.addEventListener('click', function (e) {
            if (!confirm('Confirmar a devolução deste exemplar?')) {
                e.preventDefault();
            }
        });
    });

    // Confirmação simples antes de cancelar uma reserva
    document.querySelectorAll('a[href*="/cancelar"]').forEach(function (link) {
        link.addEventListener('click', function (e) {
            if (!confirm('Deseja realmente cancelar esta reserva?')) {
                e.preventDefault();
            }
        });
    });

});