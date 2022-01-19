fetch('/guilds.json')
       .then(response => {
           if (!response.ok) {
               throw new Error("HTTP error " + response.status);
           }
        return response.json();
       })
       .then(json => {
           addJsonToGuildSelect(json);
       })
       .catch(function() {
           console.log("ERROR!");
       })

fetch('/operations.json')
       .then(response => {
           if (!response.ok) {
               throw new Error("HTTP error " + response.status);
           }
        return response.json();
       })
       .then(json => {
           addJsonToOperationSelect(json);
       })
       .catch(function() {
           console.log("ERROR!");
       })