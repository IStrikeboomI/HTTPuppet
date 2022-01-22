function getGuilds(callback) {
    fetch('/guilds.json')
           .then(response => {
               if (!response.ok) {
                   throw new Error("HTTP error " + response.status);
               }
            return response.json();
           })
           .then(json => {
               callback(json);
           })
           .catch(function() {
               console.log("ERROR!");
           })
}
function getOperations(callback) {
    fetch('/operations.json')
           .then(response => {
               if (!response.ok) {
                   throw new Error("HTTP error " + response.status);
               }
            return response.json();
           })
           .then(json => {
               callback(json);
           })
           .catch(function() {
               console.log("ERROR!");
           })
}