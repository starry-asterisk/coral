/**
 * 
 */

function weather(city){
	var key = "f7ea02283484bdc6e3c8084f304b15ee";
	if(city!=undefined&&city!=""){
		 $.getJSON("https://api.openweathermap.org/data/2.5/weather?q="+city
				 +"&appid="+key
				 +"&units="+"metric").then(function(resp){
					 displayWeather(resp,$("#side1"));
				  });
	}else{
		$.getJSON("https://api.ipify.org?format=jsonp&callback=?",
				function(json) {
					$.get('https://ipapi.co/'+json.ip+'/latlong/', function(response){
						  var latlong = response.split(',');
						  $.getJSON('https://api.openweathermap.org/data/2.5/weather?lat=' + latlong[0] 
						  + '&lon=' + latlong[1] 
						  + '&appid=' + key
						  +'&units='+'metric').then(function(resp){
							  displayWeather(resp,$("#side1"));
						  });
					});
				}
		);
	}
}
function displayWeather(resp,div){
	 var icon = getIcon(resp);
	 var tag = [];
	 tag.push($(document.createElement("table")));
	 tag.push($(document.createElement("tr")));
	 tag.push($(document.createElement("tr")));
	 tag.push($(document.createElement("td")));
	 tag.push($(document.createElement("td")));
	 tag.push($(document.createElement("td")));
	 tag.push($(document.createElement("td")));
	 div.html("");
	 div.append(tag[0]);
	 tag[0].width("100%");
	 tag[0].height("100%");
	 tag[0].append(tag[1]);
	 tag[1].height("80%");
	 tag[1].append(tag[3]);
	 tag[3].width("25%");
	 tag[1].append(tag[4]);
	 tag[1].append(tag[5]);
	 tag[5].width("25%");
	 tag[0].append(tag[2]);
	 tag[2].height("20%");
	 tag[2].append(tag[6]);
	 tag[6].attr("colspan","3");
	 tag[3].html("<i class='"+icon+"' style='font-size:3em'></i>");
	 tag[4].append("<span style='font-size:2em'>"+resp.sys.country+" - "+resp.name+"</span><br>");
	 tag[4].append("<span style='font-size:1.5em'>"+resp.weather[0].description+"</span><br>");
	 tag[4].append("<span style='font-size:1.5em'>"+resp.main.humidity+"%"+"</span><br>");
	 tag[4].append("<span style='font-size:1em'>체감온도 "+resp.main.feels_like+"°"+"</span><br>");
	 tag[5].append("<span style='font-size:2em'>"+resp.main.temp+"°"+"</span><br>");
	 tag[5].append("<span style='font-size:1em'>"+resp.main.temp_max+"° / "+resp.main.temp_min+"°"+"</span><br>");
	 var time = new Date();
	 tag[6].html("<p style='margin-left:2em;'> 날씨 제공 : OpenWeatherMap | 마지막 갱신 시간 "+time.getHours()+":"+time.getMinutes()+"</p>");
	 tag[6].css("color","#ccc");
	 tag[6].children("p").css("text-align","left");
	 tag[4].css("text-align","left");
}
function getIcon(resp) {
	
	  var prefix = 'wi wi-';
	  var code = resp.weather[0].id;
	  var icon = weatherRule[code].icon;

	  // If we are not in the ranges mentioned above, add a day/night prefix.
	  if (!(code > 699 && code < 800) && !(code > 899 && code < 1000)) {
	    icon = 'day-' + icon;
	  }

	  // Finally tack on the prefix.
	  icon = prefix + icon;
	  return icon;
};
var weatherRule ={
  "200": {
    "label": "thunderstorm with light rain",
    "icon": "storm-showers"
  },

  "201": {
    "label": "thunderstorm with rain",
    "icon": "storm-showers"
  },

  "202": {
    "label": "thunderstorm with heavy rain",
    "icon": "storm-showers"
  },

  "210": {
    "label": "light thunderstorm",
    "icon": "storm-showers"
  },

  "211": {
    "label": "thunderstorm",
    "icon": "thunderstorm"
  },

  "212": {
    "label": "heavy thunderstorm",
    "icon": "thunderstorm"
  },

  "221": {
    "label": "ragged thunderstorm",
    "icon": "thunderstorm"
  },

  "230": {
    "label": "thunderstorm with light drizzle",
    "icon": "storm-showers"
  },

  "231": {
    "label": "thunderstorm with drizzle",
    "icon": "storm-showers"
  },

  "232": {
    "label": "thunderstorm with heavy drizzle",
    "icon": "storm-showers"
  },

  "300": {
    "label": "light intensity drizzle",
    "icon": "sprinkle"
  },

  "301": {
    "label": "drizzle",
    "icon": "sprinkle"
  },

  "302": {
    "label": "heavy intensity drizzle",
    "icon": "sprinkle"
  },

  "310": {
    "label": "light intensity drizzle rain",
    "icon": "sprinkle"
  },

  "311": {
    "label": "drizzle rain",
    "icon": "sprinkle"
  },

  "312": {
    "label": "heavy intensity drizzle rain",
    "icon": "sprinkle"
  },

  "313": {
    "label": "shower rain and drizzle",
    "icon": "sprinkle"
  },

  "314": {
    "label": "heavy shower rain and drizzle",
    "icon": "sprinkle"
  },

  "321": {
    "label": "shower drizzle",
    "icon": "sprinkle"
  },

  "500": {
    "label": "light rain",
    "icon": "rain"
  },

  "501": {
    "label": "moderate rain",
    "icon": "rain"
  },

  "502": {
    "label": "heavy intensity rain",
    "icon": "rain"
  },

  "503": {
    "label": "very heavy rain",
    "icon": "rain"
  },

  "504": {
    "label": "extreme rain",
    "icon": "rain"
  },

  "511": {
    "label": "freezing rain",
    "icon": "rain-mix"
  },

  "520": {
    "label": "light intensity shower rain",
    "icon": "showers"
  },

  "521": {
    "label": "shower rain",
    "icon": "showers"
  },

  "522": {
    "label": "heavy intensity shower rain",
    "icon": "showers"
  },

  "531": {
    "label": "ragged shower rain",
    "icon": "showers"
  },

  "600": {
    "label": "light snow",
    "icon": "snow"
  },

  "601": {
    "label": "snow",
    "icon": "snow"
  },

  "602": {
    "label": "heavy snow",
    "icon": "snow"
  },

  "611": {
    "label": "sleet",
    "icon": "sleet"
  },

  "612": {
    "label": "shower sleet",
    "icon": "sleet"
  },

  "615": {
    "label": "light rain and snow",
    "icon": "rain-mix"
  },

  "616": {
    "label": "rain and snow",
    "icon": "rain-mix"
  },

  "620": {
    "label": "light shower snow",
    "icon": "rain-mix"
  },

  "621": {
    "label": "shower snow",
    "icon": "rain-mix"
  },

  "622": {
    "label": "heavy shower snow",
    "icon": "rain-mix"
  },

  "701": {
    "label": "mist",
    "icon": "sprinkle"
  },

  "711": {
    "label": "smoke",
    "icon": "smoke"
  },

  "721": {
    "label": "haze",
    "icon": "day-haze"
  },

  "731": {
    "label": "sand, dust whirls",
    "icon": "cloudy-gusts"
  },

  "741": {
    "label": "fog",
    "icon": "fog"
  },

  "751": {
    "label": "sand",
    "icon": "cloudy-gusts"
  },

  "761": {
    "label": "dust",
    "icon": "dust"
  },

  "762": {
    "label": "volcanic ash",
    "icon": "smog"
  },

  "771": {
    "label": "squalls",
    "icon": "day-windy"
  },

  "781": {
    "label": "tornado",
    "icon": "tornado"
  },

  "800": {
    "label": "clear sky",
    "icon": "sunny"
  },

  "801": {
    "label": "few clouds",
    "icon": "cloudy"
  },

  "802": {
    "label": "scattered clouds",
    "icon": "cloudy"
  },

  "803": {
    "label": "broken clouds",
    "icon": "cloudy"
  },

  "804": {
    "label": "overcast clouds",
    "icon": "cloudy"
  },


  "900": {
    "label": "tornado",
    "icon": "tornado"
  },

  "901": {
    "label": "tropical storm",
    "icon": "hurricane"
  },

  "902": {
    "label": "hurricane",
    "icon": "hurricane"
  },

  "903": {
    "label": "cold",
    "icon": "snowflake-cold"
  },

  "904": {
    "label": "hot",
    "icon": "hot"
  },

  "905": {
    "label": "windy",
    "icon": "windy"
  },

  "906": {
    "label": "hail",
    "icon": "hail"
  },

  "951": {
    "label": "calm",
    "icon": "sunny"
  },

  "952": {
    "label": "light breeze",
    "icon": "cloudy-gusts"
  },

  "953": {
    "label": "gentle breeze",
    "icon": "cloudy-gusts"
  },

  "954": {
    "label": "moderate breeze",
    "icon": "cloudy-gusts"
  },

  "955": {
    "label": "fresh breeze",
    "icon": "cloudy-gusts"
  },

  "956": {
    "label": "strong breeze",
    "icon": "cloudy-gusts"
  },

  "957": {
    "label": "high wind, near gale",
    "icon": "cloudy-gusts"
  },

  "958": {
    "label": "gale",
    "icon": "cloudy-gusts"
  },

  "959": {
    "label": "severe gale",
    "icon": "cloudy-gusts"
  },

  "960": {
    "label": "storm",
    "icon": "thunderstorm"
  },

  "961": {
    "label": "violent storm",
    "icon": "thunderstorm"
  },

  "962": {
    "label": "hurricane",
    "icon": "cloudy-gusts"
  }
};