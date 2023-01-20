const DrawCanvas = [0, 0.5, 1, 1.5, 2, 3, 3.5, 4, 4.5, 5, 5.5, 6],
  H = class {
    _canvas;
    _notes;
    _oct = 0;
    _elapsed = 0;
    _lastTime = 0;
    _screenWidth = 600;
    _screenTime = 600 * (1e3 / 60);
    _playScore = [];
    _volumeElem;
    _toneElem;
    _inited = !1;
    _currentNote = null;
    get octav() {
      return this._oct;
    }
    set octav(e) {
      this._oct = e;
    }
    get screenWidth() {
      return this._screenWidth;
    }
    set screenWidth(e) {
      (this._screenWidth = e),
        (this._screenTime = this._screenWidth * (1e3 / 60));
    }
    constructor() {
      (this._canvas = document.createElement('canvas')),
        (this._canvas.width = this._screenWidth),
        (this._canvas.height = 250),
        (this._canvas.style.width = '100%'),
        (this._notes = new Array(300).fill(-1)),
        new ResizeObserver(this._resizeCallback).observe(this._canvas);
    }
    renderElement() {
      return this._canvas;
    }
    _resizeCallback() {
      this._fitToContainer();
    }
    _fitToContainer() {
      let { offsetWidth: e, offsetHeight: t } = this._canvas;
      (this._canvas.width = e), (this._screenWidth = e);
      let r = Math.floor(e / 2);
      this._notes.length < r
        ? this._notes.unshift(...new Array(r - this._notes.length).fill(-1))
        : this._notes.length > r &&
          this._notes.splice(0, this._notes.length - r);
    }
    start(e) {
      (this._playScore = e.slice()), (this._elapsed = -1e3);
    }
    stop() {
      this._playScore = [];
    }
    get currentTime() {
      return this._elapsed;
    }
    _renderNotes(e) {
      e.save();
      let t = 1e3 / 60,
        r = this._screenWidth,
        i = r / 2;
      e.translate(i, 0);
      let s = (r * t) / 2,
        n = 40 / t,
        y = null;
      this._playScore.forEach(d => {
        if (d.note === -1) return;
        let o = (d.start - this._elapsed) / t;
        if (o > i) return;
        let p = d.length / t - 1;
        if (o + p < -i) return;
        let m = B[d.note] * 5 + (d.octav - 3) * 35 + 150 + this._oct * 5 - 2.5;
        d.start <= this._elapsed && d.start + d.length - t >= this._elapsed
          ? ((y = d), (e.fillStyle = 'orange'))
          : (e.fillStyle = 'blue'),
          e.fillRect(o + n, m, p, 5),
          d.lylic &&
            (e.save(),
            (e.fillStyle = 'black'),
            e.translate(o + n, m),
            e.scale(1, -1),
            e.fillText(d.lylic, 0, 5),
            e.restore());
      }),
        e.restore(),
        (this._currentNote = y);
    }
    getCurrentNote() {
      return this._currentNote;
    }
    pushNote(e) {
      this._notes.push(e), this._notes.shift();
    }
    update(e) {
      this._elapsed += e;
    }
    render() {
      let e = this._canvas.getContext('2d');
      e.save(),
        (e.font = '14px monospace'),
        (e.textBaseline = 'top'),
        e.clearRect(0, 0, this._canvas.width, this._canvas.height);
      let t = ['#eee', '#ddd'];
      e.scale(1, -1),
        e.translate(0, -300),
        this._renderLines(e),
        (e.globalAlpha = 0.5),
        (e.fillStyle = 'blue'),
        this._renderNotes(e),
        this._renderVoice(e),
        (e.strokeStyle = 'yellowgreen'),
        e.beginPath(),
        e.moveTo(this._screenWidth / 2, 0),
        e.lineTo(this._screenWidth / 2, 400),
        e.stroke(),
        e.restore(),
        (e.font = '30px monospace'),
        e.fillText(this._oct.toString(), 0, 20);
    }
    _renderVoice(e) {
      (e.fillStyle = 'red'),
        this._notes.forEach((t, r) => {
          if (t !== -1) {
            let i = Math.floor(t / 12) - 4,
              c = t % 12;
            e.fillRect(r, B[c] * 5 + 150 + i * 35 - 2.5, 1, 5);
          }
        });
    }
    inited() {
      this._inited = !0;
    }
    _renderLines(e) {
      (e.strokeStyle = 'black'), e.beginPath();
      for (let t = 0; t < 5; t++)
        e.moveTo(0, t * 10 + 160), e.lineTo(this._screenWidth, t * 10 + 160);
      e.stroke(), (e.strokeStyle = '#ddd'), e.beginPath();
      for (let t = 0; t < 5; t++)
        e.moveTo(0, t * 10 + 210),
          e.lineTo(this._screenWidth, t * 10 + 210),
          e.moveTo(0, t * 10 + 110),
          e.lineTo(this._screenWidth, t * 10 + 110);
      e.stroke();
    }
  };

export default DrawCanvas;
