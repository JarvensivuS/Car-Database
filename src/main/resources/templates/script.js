function getURL() {
    var element = document.getElementById("inputid").value;
    document.getElementById("form_id").action="http://localhost:8080/cars/"+element; 
}