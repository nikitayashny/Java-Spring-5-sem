async function genUserSearchButton() {
    let token = localStorage.getItem('token');
    let inputResult = document.getElementById('searchPatientCard').value;
    if(inputResult.length===0){
        await genListOfCarStuffForUser();
    }else{
        let someList = document.querySelector('.someList');
        someList.innerHTML = '';
        let listProject = await getAllCarsForUser(token);

        for (let i = 0; i < listProject.length; i++) {
            let genDiv = div();
            if (inputResult === listProject[i]['name'] || inputResult === listProject[i]['description']) {
                let genP1 = p(listProject[i]['name'] + ' ' + listProject[i]['description'] + ' ' + listProject[i]['cost']);
                let genBut = buttonWithParams('get');

                genBut.onclick = async () => {
                    await getCertainCarUser(listProject[i]);
                };
                genDiv.appendChild(genP1);
                genDiv.appendChild(genBut);
                someList.appendChild(genDiv);
            }
        }
    }
}
async function userSearch() {

    let search = document.querySelector('.search');
    let searchButton = button(await genUserSearchButton, 'Search');
    let searchPatientCard = input('text','searchPatientCard','car name');
    searchButton.id = 'docCreateButton';
    search.appendChild(searchPatientCard);
    search.appendChild(searchButton);
}
async function load() {

    let result = document.querySelector('.results');
    await generateListOfUsers(result);
    genPrev();
    await genNext();


    if (await isAuth()) {
        genLogout();
        if (await isAdmin()) {
            await genCarStuff();
            await genAdminCreate();
            await genAdminUpdate();
            await genAdminDelete();
            await genListOfCarStuffForAdmin();
            await genAdminInfo();


        } else {
            await genListOfCarStuffForUser();
            await userSearch();
            await genUserInfo();
        }
    } else {
        genLogReg(result);

    }

}