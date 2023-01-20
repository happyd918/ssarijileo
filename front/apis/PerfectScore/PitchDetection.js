import Common from './Common';

const PitchDetection = class extends Common {
  ctx;
  analyser;
  buf = new Float32Array(2048);
  pitch = -1;
  note = 0;
  octave = 0;
  inited = !1;
  constructor(e) {
    super();
    this.ctx = e;
  }
  start() {
    if (!this.inited) {
      let e = this.ctx.createAnalyser();
      (this.analyser = e), (this.analyser.fftSize = 2048), this.getUserMedia();
    }
  }
  getUserMedia() {
    let e = navigator;
    e.mediaDevices === void 0 && (e.mediaDevices = {}),
      e.mediaDevices.getUserMedia === void 0 &&
        (e.mediaDevices.getUserMedia = function (r) {
          let i =
            e.getUserMedia ||
            e.webkitGetUesrmedia ||
            e.mozGetUserMedia ||
            e.msGetUserMedia;
          return i
            ? new Promise(function (c, s) {
                i.call(navigator, r, c, s);
              })
            : Promise.reject(new Error('getUserMedia is not supported'));
        });
    let t = { audio: !0 };
    e.mediaDevices.getUserMedia(t).then(r => {
      this.ctx.createMediaStreamSource(r).connect(this.analyser),
        (this.inited = !0),
        this.emit('inited');
    });
  }
  update(e) {
    if (!this.inited) return;
    this.analyser.getFloatTimeDomainData(this.buf);
    let t = this.correlate(this.buf, this.ctx.sampleRate);
    console.log(t);
    (this.pitch = t),
      t === -1
        ? (this.note = -1)
        : ((this.note = K(t)), (this.octave = Math.floor(this.note / 12) - 1)),
      this.emit('note', this.note);
  }
  correlate(e, t) {
    if (this.isSilentBuffer(e)) return -1;
    let r = 0.2,
      i = this.trimBuffer(e, r),
      c = i.length,
      s = new Array(c).fill(0);
    for (let h = 0; h < c; h++)
      for (let T = 0; T < c - h; T++) s[h] = s[h] + i[T] * i[T + h];
    let n = 0;
    for (; s[n] > s[n + 1]; ) n++;
    let y = -1,
      d = -1;
    for (let h = n; h < c; h++) s[h] > y && ((y = s[h]), (d = h));
    let o = d,
      p = s[o - 1],
      m = s[o],
      f = s[o + 1],
      L = (p + f - 2 * m) / 2,
      D = (f - p) / 2;
    return L && (o = o - D / (2 * L)), t / o;
  }
  isSilentBuffer(e) {
    let t = e.length,
      r = 0;
    for (let i = 0; i < t; i++) r += e[i] * e[i];
    return (r = Math.sqrt(r / t)), r < 0.01;
  }
  trimBuffer(e, t = 0.2) {
    let r = e.length,
      i = 0,
      c = r - 1,
      s = 0.2;
    for (let n = 0; n < r / 2; n++)
      if (Math.abs(e[n]) < s) {
        i = n;
        break;
      }
    for (let n = 1; n < r / 2; n++)
      if (Math.abs(e[r - n]) < s) {
        c = r - n;
        break;
      }
    return e.slice(i, c);
  }
};

export default PitchDetection;
