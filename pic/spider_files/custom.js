
/**
 * @fileoverview Custom functionality to apply throughout every adsize. This
 * has a dependency on common.js and utils.js
 */
var custom = (function() {

  /**
   * Classes which our JS hooks into. Add more class names as necessary.
   * @enum
   * @private
   */
  var elementClass_ = {
    item: 'js-item',
    itemPrice: 'js-item-price',
    itemSalePrice: 'js-item-saleprice',
    itemRegularPrice: 'js-item-regularprice',
    itemCta: 'js-item-cta'
  };

  /**
  * Classes which our JS hooks. 
  * @enum
  * @private
  */
  var elementId_ = {
    gpaDataProvider: 'gpa-data-provider'
  };

  var totalCount = 0;

  /**
   * ID
   */
   var ID = {
     arrowPrevious: 'arrow-previous',
     arrowNext: 'arrow-next'
   };

  /**
   * Initialization. Called from handleAdInitialized on each page.
   */
  function init() {
    utils.log('custom.init()');
    var data = common.getAdData();
    if (!data) return;

    // If you're using the swipe gallery to display feed items.
    initItemsUsingGallery_();

    // If you're NOT using the swipe gallery to display feed items.
    //initItemsWithoutGallery_();
  }

  /**
   * Find all items used in the swipe gallery and initialize custom behavior.
   * @private
   */
  function initItemsUsingGallery_() {
    var gallery = common.getGallery();

    // Apply settings to each item in the gallery
    var items = gallery.querySelectorAll('.' + elementClass_.item);
    for (var i = 0; i < items.length; i++) {
      var item = items[i];
      initItemDisplay_(item);
    }
  }

  /**
   * Find all items used outside the gallery and initialize custom behavior.
   * @private
   */
  function initItemsWithoutGallery_() {
    // Apply settings to each item
    var items = document.querySelectorAll('.' + elementClass_.item);
    for (var i = 0; i < items.length; i++) {
      var item = items[i];
      initItemDisplay_(item);
    }
  }

  /**
   * Set the display settings for each item.
   * Add any custom functionality you need applied on load.
   * @param {Element} item Item element.
   * @private
   */
  function initItemDisplay_(item) {
    // Set mouseout.
    itemMouseOut(item);
  }

  /**
   * Handle Swipegallery Frame Shown event.
   * @param {Event} event Swipegallery frame event.
   */
  function galleryFrameShown(event) {
    var index = common.getCurrentItemIndex();
    updateArrowDisplay (index);
  }

  /**
   * Custom Mouseover interaction functionality.
   * @param {index} current index (zero based)
   */
  function updateArrowDisplay (index) {
    var itemsLenght = gallery.querySelectorAll('.' + elementClass_.item).length-1;
    var arrowPrevious = utils.getElement(ID.arrowPrevious);
    var arrowNext = utils.getElement(ID.arrowNext);
    if (index <=0) {
      utils.showElement(arrowPrevious, false);
      utils.showElement(arrowNext, true);
    } else if (index >= itemsLenght) {
      utils.showElement(arrowPrevious, true);
      utils.showElement(arrowNext, false);
    } else {
      utils.showElement(arrowPrevious, true);
      utils.showElement(arrowNext, true);
    }
    utils.log ('itemsLenght: ' + itemsLenght);
  }

  /**
   * Custom Mouseover interaction functionality.
   * @param {Element} item
   */
  function itemMouseOver(item) {
    //var data = common.getAdData();
  }

  /**
   * Custom Mouseout interaction functionality.
   * @param {Element} item
   */
  function itemMouseOut(item) {
  }

  /**
   * Custom Mouseover on CTA.
   * @param {Element} item
   */
  function itemCtaMouseover(event) {
    var cta = getItemCtaElement(event.target);
    if(cta) {
      var ctaOn = cta.children[1];
      if(ctaOn) utils.showElement(ctaOn, true);
    }
  }

  /**
   * Custom Mouseout on CTA.
   * @param {Element} item
   */
  function itemCtaMouseout(event) {
    var cta = getItemCtaElement(event.target);
    if(cta) {
      var ctaOn = cta.children[1];
      if(ctaOn) utils.showElement(ctaOn, false);
    }
  }

  /**
   * Find the item CTA element by traversing the item element.
   * @param {Element} el Element to check.
   * @return {Element}
   */
  function getItemCtaElement(el) {
    var cta = utils.closestElement(el, function(el) {
      return el.classList.contains(elementClass_.itemCta);
    });
    return cta;
  }

  /*
  * Custom transform dynamic data for data validation.
  */
  function transformDynamicData (nameLength, btnLength, priceLength) {
    var dataProvider = document.querySelector('#' + elementId_.gpaDataProvider);
    dataProvider.addDataTransformer(function(dynamicData) {

        /**
        * Convert Japanese fullspace to ASCII halfspace.
        */
        if(dynamicData.Headline){
          convertLongNameToShortName(dynamicData.Headline, "cta", btnLength);
        }
        if(dynamicData.Product){
          totalCount = dynamicData.Product.length;
          for (var i = 0, l = dynamicData.Product.length; i < l; i++) {
            convertLongNameToShortName(dynamicData.Product[i], "name", nameLength);
            convertLongNameToShortName(dynamicData.Product[i], "price", priceLength);
            convertLongNameToShortName(dynamicData.Product[i], "regularPrice", priceLength);
            
            //convertLongNameToShortName(dynamicData.Product[i], "description", desLength);
          }
        } 
    });
  }

  function convertLongNameToShortName (item, attr, charaLength) {
   
        item[attr] = convertLongText(item[attr], charaLength);

      
    }

  function convertLongText(text, length) {
    var newText;
    if(text && text.length > length) {
      newText = text.slice(0,length)+'...';
    }
    else {
      newText = text;
    }
    
    return newText;
  }

  function getDataCount() {
    
    return totalCount;
  }

  // Add any methods here which you want to be accessible from your html.
  return {
    init: init,
    itemMouseOver: itemMouseOver,
    itemMouseOut: itemMouseOut,
    itemCtaMouseover: itemCtaMouseover,
    itemCtaMouseout: itemCtaMouseout,
    galleryFrameShown: galleryFrameShown,
    getDataCount: getDataCount,
    transformDynamicData: transformDynamicData
  };

})();
