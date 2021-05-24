function getDepartments() {
    $.get("http://localhost:8080/departments-js", function (data) {
        let html = '';

        for (let i = 0; i < data.length; i++) {
            html += '<tr>' +
                '<td>' + data[i].id + '</td>' +
                '<td>' + data[i].name + '</td>' +
                '</tr>'
        }
        html += "";
        $('#table_header').after(html);
    })
}