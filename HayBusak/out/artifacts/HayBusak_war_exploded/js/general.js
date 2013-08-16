$(document).ready(function () {
    /*lang();*/
});
function lang() {
    $(".lang").click(function () {
        var lang = $(this).find("input[name='lang']").val();
        $form = $("#langForm");
        $form.find("input[name='lang']").val(lang);
        $form.submit();
    });
}
