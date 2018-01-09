/*
 * Clients :: Creation & List
 */
var resetClientFrom = function() {
	$('#sectionTitle').html("Nouveau Client");
//	$('#clientAddEditForm').attr('action', '/clients/create');
	$('#clientAddEditForm').append()
	$('#client_code').remove();
}

var selectClient = function(clientId) {
	var $row = $('tr#client' + clientId);
	var cells = $('td', $row)
	
	$('<input>').attr({
    		type: 'hidden', id: 'client_code', name: 'code', value: cells[0].innerHTML
    }).appendTo('form');

	$('#sectionTitle').html("Edition Client");
	$('#client_code').val(cells[0].innerHTML);
 	$('#client_nom').val(cells[1].innerHTML);
 	$('#client_email').val(cells[2].innerHTML);
}

/*
 * Bank Account :: Creation
 */
$('.compte-type-switcher').on('click', function () {
    var compteType = $(this).data('type');
	$('#typeCompte').val(compteType);

    if(compteType === "CC") {
    		$('#decouvert_section').removeClass("hidden");
    		$('#taux_section').addClass("hidden");
    } else {
		$('#taux_section').removeClass("hidden");
		$('#decouvert_section').addClass("hidden");
    }
  })

/*
 * Bank Account :: Operations
 */
$('.operation-type-switcher').on('click', function () {
    var typeOperation = $(this).data('type');
	$('#typeOperation').val(typeOperation);

    if(typeOperation === "virement") {
		$('#toAccount').removeClass("hidden");
    } else {
		$('#toAccount').addClass("hidden");
    }
});