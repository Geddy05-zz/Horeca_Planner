</div>
<!-- /.container-fluid -->

</div>
<!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- Morris Charts JavaScript -->
<script src="/js/plugins/morris/raphael.min.js"></script>
<script src="/js/plugins/morris/morris.min.js"></script>
<script>
    function logOut(){
        firebase.auth().signOut().then(function() {
            window.location = "/";
        }, function(error) {
            console.log(error);
        });
    }
</script>
</body>
</html>
