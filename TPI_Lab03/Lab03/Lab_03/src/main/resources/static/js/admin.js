async function genCarStuff() {
    let info = document.querySelector(".personalInfo");
    let name = input('text', 'name', 'Name', '');
    let description = input('text', 'description', 'Description', '');
    let cost = input('number', 'cost', 'Number', '');
    let expirationDate = input('date', 'date', 'Expiration date', '');


    info.appendChild(name);
    info.appendChild(description);
    info.appendChild(cost);
    info.appendChild(expirationDate);
}

async function genAdminCreate() {

    let create = document.querySelector('.create');
    let createButton = button(await genAdminCreateButton, 'Create');
    createButton.id = 'docCreateButton';
    create.appendChild(createButton);

}

async function genAdminDelete() {

    let create = document.querySelector('.create');
    let deleteButton = button(genAdminDeleteButton, 'Delete');
    create.appendChild(deleteButton);
}

async function genListOfCarStuffForAdmin() {
    let token = localStorage.getItem('token');
    let someList = document.querySelector('.someList');
    someList.innerHTML = '';
    let listProject = await getAllCarsForAdmin(token);

    for (let i = 0; i < listProject.length; i++) {
        let genDiv = div();
        let genP1 = p(listProject[i]['name'] + ' ' + listProject[i]['description'] + ' ' + listProject[i]['cost']);
        let genBut = buttonWithParams('get');

        genBut.onclick = async () => {
            await getCertainCar(listProject[i]);
        };
        genDiv.appendChild(genP1);
        genDiv.appendChild(genBut);
        someList.appendChild(genDiv);
    }
}

async function getCertainCar(softObj) {
    console.log(softObj);
    let token = localStorage.getItem('token');
    let soft = await adminGetCarByName(softObj['name'], token);

    document.getElementById('name').value = soft['name'];
    document.getElementById('description').value = soft['description'];
    document.getElementById('cost').value = soft['cost'];
    let date = soft['expirationDate'];

    date = date.substr(0, date.indexOf('T'))
    console.log(date)
    document.getElementById('date').value = date;
}

async function genAdminCreateButton() {
    let token = localStorage.getItem('token');

    let name = document.getElementById('name').value;
    let description = document.getElementById('description').value;
    let cost = document.getElementById('cost').value;
    let expirationDate = document.getElementById('date').value;

    let isNotExist = await isCarExistByName({name: name}, token);
    let errMes = document.getElementById('errMes');


    if (validateCar() && await isAuth() && isNotExist.ok) {
        let name = document.getElementById('name').value;


        let data = {
            name: name,
            description: description,
            cost: cost,
            expirationDate: expirationDate
        };
        await createCar(data, token);
        console.log(data);
        errMes.innerHTML = 'Created';
        await genListOfCarStuffForAdmin();
    } else {
        errMes.innerHTML = 'Not all fields are correct or document exist';
    }
}

async function genAdminDeleteButton() {
    let errMes = document.getElementById('errMes');
    let token = localStorage.getItem('token');
    let name = document.getElementById('name').value;
    let isNotExist = await isCarExistByName({name: name}, token);
    if (await isAuth() && !isNotExist.ok) {

        let project = await adminGetCarByName(name, token);
        let isNotExist = await isUserRentExistByCarId({id: project['id']}, token);
        if (!isNotExist.ok) {
            await deleteCarByNameA({name: name}, token);
            errMes.innerHTML = 'deleted';
            await genListOfCarStuffForAdmin();

        } else {
            errMes.innerHTML = 'u cant delete this product while product had subed users';
        }

    } else {
        errMes.innerHTML = 'nothing to delete';
    }
}

function validateCar() {
    let nameL = document.getElementById('name').value.length;
    let descriptionL = document.getElementById('description').value.length;
    let cost = document.getElementById('cost').value;
    let expirationDate = document.getElementById('date').value;

    if (!(nameL >= 2 && nameL <= 16)) {
        return false;
    }
    if (!(descriptionL >= 4)) {
        return false;
    }
    if (cost === null) {
        return false;
    }
    if (expirationDate === null) {
        return false;
    }
    return true;

}

async function isAdmin() {
    let user = await getUser();
    return user['role'] === 'ROLE_ADMIN';
}

async function genAdminUpdateButton() {
    let errMes = document.getElementById('errMes');
    let token = localStorage.getItem('token');
    let name = document.getElementById('name').value;
    let description = document.getElementById('description').value;
    let cost = document.getElementById('cost').value;
    let expirationDate = document.getElementById('date').value;
    let isNotExist = await isCarExistByName({name: name}, token);
    if (await isAuth() && !isNotExist.ok) {

        let res = await adminGetCarByName(name, token);

        if (validateCar()) {
            await updateCar({
                id: res['id'],
                name: name,
                description: description,
                cost: cost,
                expirationDate: expirationDate

            }, token);
            errMes.innerHTML = 'updated';
            await genListOfCarStuffForAdmin();
        } else {
            errMes.innerHTML = 'not validate data';
        }

    } else {
        errMes.innerHTML = 'nothing to update';
    }
}

async function genAdminUpdate() {
    let create = document.querySelector('.update');
    let deleteButton = button(genAdminUpdateButton, 'update');
    create.appendChild(deleteButton);
}

async function genAdminInfo() {
    let token = localStorage.getItem('token');
    let usersRent = await getAllUserRentByCarExpirationDateLessThan({date: new Date()}, token);

    console.log(usersRent);
    let info = document.querySelector('.neededInfo');
    let table = document.createElement('table');
    info.innerHTML = '';
    table.setAttribute('class', 'table');

    for (let i = 0; i < usersRent.length; i++) {

        if (i === 0) {
            let tr = document.createElement('tr');
            let th1 = document.createElement('th');
            th1.innerHTML = 'user name';
            let th2 = document.createElement('th');
            th2.innerHTML = 'user surname';
            let th3 = document.createElement('th');
            th3.innerHTML = 'car name';
            let th4 = document.createElement('th');
            th4.innerHTML = 'expiration date';
            let th5 = document.createElement('th');
            th5.innerHTML = 'Cancel rent?';

            tr.appendChild(th1);
            tr.appendChild(th2);
            tr.appendChild(th3);
            tr.appendChild(th4);
            tr.appendChild(th5);
            table.appendChild(tr);
        }
        let isSet = usersRent[i]['rent'];
        if (isSet === false) {
            let tr = document.createElement('tr');
            for (let y = 0; y < 7; y++) {
                let th = document.createElement('th');
                let Car = usersRent[i]['car'];
                console.log(usersRent[i]['userName']);
                switch (y) {
                    case 0: {
                        th.innerHTML = usersRent[i]['userName'];
                        break;
                    }
                    case 1: {
                        th.innerHTML = usersRent[i]['userSurname'];
                        break;
                    }
                    case 2: {
                        th.innerHTML = Car['name'];
                        break;
                    }
                    case 3: {
                        th.innerHTML = Car['expirationDate'];
                        break;
                    }
                    case 4: {
                        let subButton = buttonWithParams('Cancel');
                        subButton.onclick = async () => {
                            await rent(usersRent[i], token);
                        };
                        th.appendChild(subButton);
                        break;
                    }
                }
                tr.appendChild(th);

            }
            table.appendChild(tr);
        }

    }
    info.appendChild(table);
}

async function rent(userProductsListElement, token) {
    await setUserRentFormById({
        id: userProductsListElement['id'],
        rent: true
    }, token);
    await genAdminInfo();
}





