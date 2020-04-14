<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Space Ships</title>
    <meta id="root" about="${pageContext.request.contextPath}">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts.js">
    </script>

</head>
<body  onload="loadContent('${pageContext.request.contextPath}','', 1);">
<div class="container">


            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Error!</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="alert alert-danger" role="alert" id="error-text">
                                This is a danger alert—check it out!
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>



    <h1 style="text-align: center; margin-top: 50px; margin-bottom: 30px">Spaceship rating</h1>

        <label style="float: left ; margin-right: 10px" for="order">Order by: </label>
        <select style="float: left" onchange="processSearch('${pageContext.request.contextPath}', 1)" style="margin-left: 5px" id="order"
                class="form-control-sm">
            <option selected>Id</option>
            <option>Speed</option>
            <option>Prod year</option>
            <option>Rating</option>
        </select>


        <select style="float: right" onchange="processSearch('${pageContext.request.contextPath}', 1)" style="margin-left: 5px" id="limit"
                class="form-control-sm">
            <option>1</option>
            <option selected>3</option>
            <option>5</option>
            <option>10</option>
            <option>20</option>
        </select>
     <label style="float: right; margin-right: 10px" for="limit">Ships in a page: </label>

<br>

    <table style="margin-top: 10px" class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Planet</th>
            <th scope="col">Ship type</th>
            <th scope="col">Prod year</th>
            <th scope="col">Pre-owned</th>
            <th scope="col">Max speed</th>
            <th scope="col">Crew size</th>
            <th scope="col">Rating</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody id="mainTable">
        </tbody>
    </table>
    <h5 id="count" style="float: right; margin-right: 20px"></h5>
    <div>
        <ul id="pagging-bar" class="pagination pagination-sm justify-content-center">

        </ul>
    </div>


    <button style="margin-bottom: 15px" type="button" class="btn btn-info" onclick="clickCreate()">Create new ship</button>
    <form style="background-color: #E9ECEF; padding: 20px; border-radius: 10px; display: none" id="createButton">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputNameNew">Name</label>
                <input type="text" class="form-control" id="inputNameNew" placeholder="Name">
            </div>
            <div class="form-group col-md-6">
                <label for="inputPlanetNew">Planet</label>
                <input type="text" class="form-control" id="inputPlanetNew" placeholder="Planet">
            </div>
        </div>
        <div class="form-row">

            <div class="form-group col-md-3" style="padding: 0px 10px">
                <label>Prod year</label>
                <div class="form-row">
                        <input type="number" min="0" class="form-control" id="inputProdYearNew">
                </div>
            </div>
            <div class="form-group col-md-3" style="padding: 0px 10px">
                <label>Crew size</label>
                <div class="form-row">
                        <input type="number" min="0" class="form-control" id="inputCrewSizeNew">
                </div>
            </div>
            <div class="form-group col-md-3" style="padding: 0px 10px">
                <label>Max speed</label>
                <div class="form-row">
                        <input type="number" min="0" max="1" step="0.01" class="form-control" id="inputSpeedNew">
                </div>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-3">
                <label for="inputShipType">Ship type</label>
                <select id="inputShipTypeNew" class="form-control">
                    <option selected>Transport</option>
                    <option>Military</option>
                    <option>Merchant</option>
                </select>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6" style="padding: 0px 10px">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadioNew1"
                           value="true" checked="checked">
                    <label class="form-check-label" for="inlineRadio2">Pre-owned</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadioNew2"
                           value="false">
                    <label class="form-check-label" for="inlineRadio3">New</label>
                </div>
            </div>
        </div>
        <button type="button" onclick="processCreate('${pageContext.request.contextPath}')" class="btn btn-success">
            Create
        </button>
    </form>

    <h3 style="margin-top: 50px">Filter options:</h3>
    <form style="background-color: #E9ECEF; padding: 20px; border-radius: 10px" class="mb-3">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputName">Name</label>
                <input type="text" class="form-control" id="inputName" placeholder="Name">
            </div>
            <div class="form-group col-md-6">
                <label for="inputPlanet">Planet</label>
                <input type="text" class="form-control" id="inputPlanet" placeholder="Planet">
            </div>
        </div>
        <div class="form-row">

            <div class="form-group col-md-3" style="padding: 0px 10px">
                <label>Prod year between</label>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <input type="number" min="0" class="form-control" id="inputProdYearAfter">
                    </div>
                    <div class="form-group col-md-6">
                        <input type="number" min="0" class="form-control" id="inputProdYearBefore">
                    </div>
                </div>
            </div>
            <div class="form-group col-md-3" style="padding: 0px 10px">
                <label>Crew size between</label>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <input type="number" min="0" class="form-control" id="inputCrewSizeMin">
                    </div>
                    <div class="form-group col-md-6">
                        <input type="number" min="0" class="form-control" id="inputCrewSizeMax">
                    </div>
                </div>
            </div>
            <div class="form-group col-md-3" style="padding: 0px 10px">
                <label>Max speed between</label>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <input type="number" min="0" max="1" step="0.01" class="form-control" id="inputSpeedMin">
                    </div>
                    <div class="form-group col-md-6">
                        <input type="number" min="0" max="1" step="0.01" class="form-control" id="inputSpeedMax">
                    </div>
                </div>
            </div>
            <div class="form-group col-md-3" style="padding: 0px 10px">
                <label>Rating between</label>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <input type="number" min="0" max="50" step="0.1" class="form-control" id="inputRatingMin">
                    </div>
                    <div class="form-group col-md-6">
                        <input type="number" min="0" max="50" step="0.1" class="form-control" id="inputRatingMax">
                    </div>
                </div>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-3">
                <label for="inputShipType">Ship type</label>
                <select id="inputShipType" class="form-control">
                    <option selected>Any</option>
                    <option>Transport</option>
                    <option>Military</option>
                    <option>Merchant</option>
                </select>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6" style="padding: 0px 10px">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1"
                           value="null" checked="checked">
                    <label class="form-check-label" for="inlineRadio1">Any</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2"
                           value="true">
                    <label class="form-check-label" for="inlineRadio2">Pre-owned</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio3"
                           value="false">
                    <label class="form-check-label" for="inlineRadio3">New</label>
                </div>
            </div>
        </div>
        <button type="button" onclick="processSearch('${pageContext.request.contextPath}',1)" class="btn btn-primary">
            Accept
        </button>
    </form>


</div>
</body>
</html>
