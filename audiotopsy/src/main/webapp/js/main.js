// URLs for the RESTful services

var yearwisestats = "http://ec2-54-215-169-137.us-west-1.compute.amazonaws.com:8080/audiotopsy/rest/music/yearwisestats";
var yeartoptracks = "http://ec2-54-215-169-137.us-west-1.compute.amazonaws.com:8080/audiotopsy/rest/music/yeartoptracksk";
var yearwisestats = "http://ec2-54-215-169-137.us-west-1.compute.amazonaws.com:8080/audiotopsy/rest/music/yearwisestats";
var lastKey;
var searchQuery;
var chart;

$(window).load(function() {
	$('button[value="1"]').addClass("active");
	$("#time").hide();
	$("#chart1").hide();

});

$('button[name="model"]').click(
		function() {
			if ($(this).val() == "3") {
				$('#urlList li').remove();

				var result = null;
				$.ajax({
					url : yearwisestats,
					type : 'GET',
					async : false,
					success : function(data) {
						result = data;
					}
				});

				nv.addGraph(function() {

					chart = nv.models.lineChart().useInteractiveGuideline(true)
							.transitionDuration(350).showLegend(true)
							.showYAxis(true).showXAxis(true);

					chart.xAxis.axisLabel("Year").tickFormat(function(d) {
						return d
					});

					chart.yAxis.axisLabel('Hotttnesss').tickFormat(
							d3.format(',.2f'));

					d3.select('#chart1 svg').datum(result).call(chart);

					nv.utils.windowResize(function() {
						chart.update()
					});

					chart.dispatch.on('stateChange', function(e) {
						nv.log('New State:', JSON.stringify(e));
					});

					return chart;
				});

				$("#chart1").show();
			} else {
				$("#chart1").hide();
			}
		})

// Register listeners
$('#btnSearch').click(function() {

	search($('#searchQuery').val());
	searchQuery = $('#searchQuery').val();
	return false;
});

// Trigger search when pressing 'Return' on search key input field
$('#searchQuery').keypress(function(e) {
	if (e.which == 13) {
		search($('#searchQuery').val());
		searchQuery = $('#searchQuery').val();
		e.preventDefault();
		return false;
	}
});

function search(searchQuery) {
	if (searchQuery != '') {
		$.ajax({
			type : 'GET',
			url : yeartoptracks,
			data : {
				year : searchQuery,
				key : ''
			},
			success : function(data) {
				$('#urlList li').remove();
				renderList(data["year_top_tracks"][0]);
				lastKey = data["last_key"][0];
			}
		});
	}

}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an
	// object (not an 'array of one')

	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	console.log('reached renderList: ' + list[0].artist_name);
	for (var i = 0; i < list.length; i++) {
		$('#urlList').append(
				'<li><span> Artist: ' + list[i].artist_name
						+ ' </span><span> Song Title: ' + list[i].title
						+ ' </span><p> Album: ' + list[i].album + ' </p></li>');
	}
	$("#urlList").show();
}

$(window).scroll(
		function() {
			if ($(window).scrollTop() == $(document).height()
					- $(window).height()
					&& searchQuery
					&& $('button[name="model"].active').val() != "3") {

				$('div#loadmoreajaxloader').show();

				$.ajax({
					type : 'GET',
					url : yeartoptracks,
					data : {
						year : searchQuery,
						key : lastKey
					},
					success : function(data) {
						console.log('reached action2: ' + data);
						renderList(data);
						renderList(data["year_top_tracks"][0]);
						lastKey = data["last_key"][0];

					}
				});

				$('div#loadmoreajaxloader').hide();
			}
		});
