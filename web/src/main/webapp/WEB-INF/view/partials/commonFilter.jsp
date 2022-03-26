<h2>Minsk cafes<span id="filterCount" class="badge badge-secondary badge-pill"></span></h2>
<form method="get">
    <div class="form-group">
        <select name="cafeType" class="form-control">
            <option value="ALL">All</option>
            <option value="COFFEE_HOUSE">Coffee house</option>
            <option value="BAKERY_CAFE">Bakery</option>
            <option value="ICE_CREAM_CAFE">Ice-cream</option>
            <option value="GRILLE_CAFE">Grille</option>
            <option value="BAR_CAFE">Bar</option>
            <option value="CAFETERIA">Cafeteria</option>
        </select>
    </div>
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="minCost" class="col-sm-4 col-form-label">Min cost</label>
            <input type="number" id="minCost" class="form-control" name="minCost" min="0" value="0" step=".01">
        </div>
        <div class="form-group col-md-6">
            <label for="maxCost" class="col-sm-4 col-form-label">Max cost</label>
            <input type="number" id="maxCost" class="form-control" name="maxCost" min="0" value="0" step=".01">
        </div>
    </div>
    <button type="submit" class="btn btn-block btn-primary">Search</button>
</form>
<br>