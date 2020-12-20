$('body').prop("class", "sidebar-mini layout-fixed sidebar-collapse")

var baseUrl = location.protocol+'//'+location.hostname+(location.port ? ':'+location.port: '');
var diagramUrl = `${baseUrl}/bpmn/base.bpmn`;

// modeler instance
var bpmnModeler = new BpmnJS({
  container: '#canvas',
  keyboard: {
    bindTo: window
  }
});

//Save diagram contents and print them to the console.
async function exportDiagram() {
  try {
    var result = await bpmnModeler.saveXML({ format: true });
    alert('Diagram exported. Check the developer tools!');
    console.log('DIAGRAM', result.xml);
  } catch (err) {
    alert('could not save BPMN 2.0 diagram!');
    console.error('could not save BPMN 2.0 diagram', err);
  }
}

/**
 * Open diagram in our modeler instance.
 * @param {String} bpmnXML diagram to display
 */
async function openDiagram(bpmnXML) {
  // import diagram
  try {
  var xmlBase =  "<?xml version=\"1.0\" encoding=\"UTF-8\"?><definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" targetNamespace=\"\" xsi:schemaLocation=\"http://www.omg.org/spec/BPMN/20100524/MODEL http://www.omg.org/spec/BPMN/2.0/20100501/BPMN20.xsd\"><collaboration id=\"Collaboration_1k4vxpn\"><participant id=\"Participant_0axkjtm\" name=\"NombreProceso\" processRef=\"Process_0g3egdl\" /></collaboration><process id=\"Process_0g3egdl\"><laneSet id=\"LaneSet_0el1pb8\"><lane id=\"Lane_1wcf2uc\" name=\"Participante1\" /></laneSet></process><bpmndi:BPMNDiagram id=\"sid-74620812-92c4-44e5-949c-aa47393d3830\"><bpmndi:BPMNPlane id=\"sid-cdcae759-2af7-4a6d-bd02-53f3352a731d\" bpmnElement=\"Collaboration_1k4vxpn\"><bpmndi:BPMNShape id=\"Participant_0axkjtm_di\" bpmnElement=\"Participant_0axkjtm\" isHorizontal=\"true\"><omgdc:Bounds x=\"80\" y=\"100\" width=\"600\" height=\"250\" /></bpmndi:BPMNShape><bpmndi:BPMNShape id=\"Lane_1wcf2uc_di\" bpmnElement=\"Lane_1wcf2uc\" isHorizontal=\"true\"><omgdc:Bounds x=\"110\" y=\"100\" width=\"570\" height=\"250\" /></bpmndi:BPMNShape></bpmndi:BPMNPlane></bpmndi:BPMNDiagram></definitions>";    
  await bpmnModeler.importXML(xmlBase);

  // access modeler components
  var canvas = bpmnModeler.get('canvas');
  //var overlays = bpmnModeler.get('overlays');

  //Evento click de los elementos
  canvas._eventBus.on('element.click', function(e) {
    // e.element = the model element
    // e.gfx = the graphical element
    hideForm();
            
    switch(e.element.type) {
      case "bpmn:Task":
        $(".config-actividad").addClass("show-form");
              $("#agregar-variable").removeClass("not-show");
              if(e.element.businessObject.name){
                $("#nombre-actividad").val(e.element.businessObject.name);
              }
      break;
      case "bpmn:Lane":
        $(".config-actividad").addClass("show-form");
                $("#set-participante").removeClass("not-show");
        break;
      default:
        // code block
    }
            
    console.log(e.element);
    console.log(e.element.businessObject.name);
  });

  // zoom to fit full viewport
  canvas.zoom('fit-viewport');

  // add marker
  canvas.addMarker('SCAN_OK', 'needs-discussion');
  } catch (err) {
    console.error('could not import BPMN 2.0 diagram', err);
  }
}

    // load external diagram file via AJAX and open it
$.get(diagramUrl, openDiagram, 'text');
        
$("#close-form").click(function() {
  hideForm();
});
        
function hideForm(){
  $(".config-actividad").removeClass("show-form");
  $("#set-participante").addClass("not-show");
  $("#agregar-variable").addClass("not-show");
}