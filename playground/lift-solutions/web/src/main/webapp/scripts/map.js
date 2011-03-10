        var map;

        function initmap(lat, lng) {
        if (GBrowserIsCompatible()) {
        map = new GMap2(document.getElementById("map_canvas"));
        map.setCenter(new GLatLng(lat,lng), 2);
        map.setUIToDefault();
        }
        }

        function initpoint(lat, lng, txt) {
        if (GBrowserIsCompatible()) {
        initmap(lat, lng)
        showpoint(lat, lng, txt, true)
        }
        }

        function updatepoint(lat, lng, txt) {
        if (GBrowserIsCompatible()) {
        map.clearOverlays()
        showpoint(lat, lng, txt, false)
        }
        }

        function showpoint(lat, lng, txt, center) {
        var point = new GLatLng(lat,lng);
        var marker = new GMarker(point);
        map.addOverlay(marker);
        marker.openInfoWindowHtml(txt);
        GEvent.addListener( marker, "click", function() { marker.openInfoWindow(txt); });
        if(center) {
            map.setCenter(new GLatLng(lat,lng), 11);
        }
        map.setUIToDefault();
        }




        var geoXml;
        function initkmlmap() {
        if (GBrowserIsCompatible()) {
        geoXml = new GGeoXml("http://gmaps-samples.googlecode.com/svn/trunk/ggeoxml/cta.kml");
        //geoXml = new GGeoXml("http://localhost:9090/map/1234/map.kml");
        map = new GMap2(document.getElementById("map_canvas"));
        map.setCenter(new GLatLng(41.875696,-87.624207), 11);
        map.setUIToDefault();
        map.addOverlay(geoXml);

        }
        }


        // $(document).ready(function () {
        // initialize();
        // });

        // ]]>
