let showEventsButton = document.getElementById('show-events')

showEventsButton.addEventListener('click', reloadEvents)
let divWithClassRow = document.getElementById('row')
function reloadEvents() {
    divWithClassRow.innerHTML = ""

    fetch('http://localhost:8080/events/api/all')
        .then(response => response.json())
        .then(json => json.forEach(event => {

            let col12CollG6 = document.createElement('div')
            col12CollG6.classList.add('col-12', 'col-lg-6')
            divWithClassRow.appendChild(col12CollG6);
            let singleFeatureEventsArea = document.createElement('div')
            singleFeatureEventsArea.classList.add('single-feature-events-area', 'd-sm-flex', 'align-items-center', 'wow', 'fadeInUpBig')
            col12CollG6.appendChild(singleFeatureEventsArea)
            let featureEventsThumb = document.createElement('div')
            featureEventsThumb.classList.add('feature-events-thumb')
            singleFeatureEventsArea.appendChild(featureEventsThumb)

            let img = document.createElement('img')
            img.src = event.imgUrl
            img.alt
            featureEventsThumb.appendChild(img)

            let dateMapArea = document.createElement('div')
            dateMapArea.classList.add('date-map-area', 'd-flex')
            featureEventsThumb.appendChild(dateMapArea)
            let dayAndTime = document.createElement('a')
            dayAndTime.text = event.dayAndTime
            dateMapArea.appendChild(dayAndTime)

            let featureEventsContent = document.createElement('div')
            featureEventsContent.classList.add('feature-events-content')
            singleFeatureEventsArea.appendChild(featureEventsContent)
            let name = document.createElement('h5')
            name.textContent = event.name
            featureEventsContent.appendChild(name)
            let destination = document.createElement('h6')
            destination.textContent = event.destination["name"]
            featureEventsContent.appendChild(destination)
            let description = document.createElement('p')
            description.textContent = event.description
            featureEventsContent.appendChild(description)

        }));

}